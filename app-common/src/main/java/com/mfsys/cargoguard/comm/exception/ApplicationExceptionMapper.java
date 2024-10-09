package com.backend.tms.comm.exception;

import java.io.Serializable;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionMapper.class);

    // Handle generic exceptions
    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<GeneralError> handleConflict(Exception ex, WebRequest request) {
        logExceptionStackTrace(ex);
        return new ResponseEntity<GeneralError>(new GeneralError(ex.getMessage(), MDC.get("request_id")),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle Application-specific exceptions
    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<APIError> handleConflict(ApplicationException ex, WebRequest request) {
        logExceptionStackTrace(ex);
        return new ResponseEntity<APIError>(new APIError(ex.getErrorCode(), ex.getArguments(), ex.getLocalizedMessage(),
            MDC.get("request_id")), HttpStatus.BAD_REQUEST);
    }

    // Handle DataIntegrityViolationException (duplicate entries, etc.)
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<APIError> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        logExceptionStackTrace(ex);

        // Extract the root cause of the exception
        String rootCauseMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        String errorCode;
        HttpStatus status;
        String message;

        // Determine the type of Data Integrity Violation based on the root cause message
        if (rootCauseMessage.contains("Duplicate entry")) {
            // Handle duplicate entry constraint violations
            errorCode = "DUPLICATE_ENTRY";
            message = "Duplicate entry error: " + rootCauseMessage;
            status = HttpStatus.CONFLICT; // 409 Conflict
        } else if (rootCauseMessage.contains("not-null property")) {
            // Handle not-null constraint violations
            errorCode = "NOT_NULL_CONSTRAINT";
            message = "Null value not allowed for the field: " + extractFieldName(rootCauseMessage);
            status = HttpStatus.BAD_REQUEST; // 400 Bad Request
        } else {
            // Generic Data Integrity Violation
            errorCode = "DATA_INTEGRITY_VIOLATION";
            message = "Data integrity violation: " + rootCauseMessage;
            status = HttpStatus.BAD_REQUEST; // 400 Bad Request
        }

        return new ResponseEntity<>(
            new APIError(errorCode, new Object[] {}, message, MDC.get("request_id")),
            status
        );
    }


    // Handle RuntimeExceptions
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<APIError> handleRuntimeException(RuntimeException ex, WebRequest request) {
        logExceptionStackTrace(ex);

        String message = "Runtime exception occurred: " + ex.getMessage();
        return new ResponseEntity<>(new APIError(message, new Object[]{}, message, MDC.get("request_id")),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Utility method to extract field name from the root cause message
    private String extractFieldName(String rootCauseMessage) {
        // Example root cause message: "not-null property references a null or transient value: com.backend.tms.auth.model.User.countryCode"
        if (rootCauseMessage.contains(":")) {
            return rootCauseMessage.substring(rootCauseMessage.lastIndexOf(":") + 1).trim();
        }
        return "Unknown field";
    }


    private void logExceptionStackTrace(Exception ex) {
        ExceptionDAO exceptionDAO = ExceptionUtil.toExceptionDAO(ex);
        LOGGER.error(exceptionDAO.getRequestId(), exceptionDAO.getExceptionArgs());
    }

    private static class GeneralError implements Serializable {

        private static final long serialVersionUID = 1L;

        private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        private String debugMessage;
        private String trackingId;

        public GeneralError(String debugMessage, String trackingId) {
            this.debugMessage = debugMessage;
            this.trackingId = trackingId;
        }

        public GeneralError() {}

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public String getDebugMessage() {
            return debugMessage;
        }

        public String getTrackingId() {
            return trackingId;
        }
    }

    public static class APIError extends GeneralError implements Serializable {

        private static final long serialVersionUID = 1L;

        private String errorCode;
        private Object[] arguments;

        public APIError(String errorCode, Object[] arguments, String debugMessage, String trackingId) {
            super(debugMessage, trackingId);
            this.errorCode = errorCode;
            this.arguments = arguments;
        }

        public APIError(String errorCode, Object[] arguments) {
            super();
            this.errorCode = errorCode;
            this.arguments = arguments;
        }

        public APIError() {}

        public String getErrorCode() {
            return errorCode;
        }

        public Object[] getArguments() {
            return arguments;
        }
    }
}
