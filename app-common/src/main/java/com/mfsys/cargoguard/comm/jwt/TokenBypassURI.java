package com.backend.tms.comm.jwt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public interface TokenBypassURI {

    List<String> URIs = new ArrayList<String>(Arrays.asList(
            "/shield/auth/user/login/authenticate",
            "/shield/auth/user/register",
            "/shield/auth/resend-otp",
            "/shield/auth/refreshtoken",
            "/shield/api/generateInternalToken",
            "/shield/auth/user/register",
            "/shield/auth/device",
            "/shield/auth/mobileUser/authenticate",
            "/shield/auth/verifyMobilelogin-otp",
            "/shield/auth/verifylogin-otp",
            "/shield/auth/verifysignup-otp",
            "/shield/auth/updateCustomerApplication",
            "/shield/realtime/location",
            "/shield/user/forgotPassword",
            "/shield/auth/userLog",
            "/shield/refreshtoken",
            "/shield/customer/register",
            "/shield/customer/register/checkid",
            "/shield/customer/register/checkaccountno",
            "/notification/otp/email",
            "/shield/getLoadStatus",
            "/shield/updateLoadStatus",
            "/shield/submitReview",
            "/shield/verify-truck",
            "/shield/proof-of-delivery",
            "/shield/proof-of-pickup",
            "/shield/schedule-for-pickup",
            "/shield/deliveryRadious",
            "/shield/pickupRadious",
            "/shield/getProofOfPickup",
         //   "/shield/isDocumentVerified",
            "/shield/getProofOfDelivery",
            "/shield/getProofOfverifyTruck",
            "/shield/saveShipmentCordinates",
            "/shield/getShipmentCordinates",
            "/shield/verifyImages",
            "/shield/getVerifyImages",
            "/shield/addDOandBIL",
            "/shield/getDOandBIL",
            "/shield/getReview",
            "/shield/stopTracking",
            "/shield/enroute",
            "/shield/shipments/status",
            "/shield/StatusShedule",
            "/shield/shipments/setDriverId",
            "/shield/getAllVerifiedDocuments",
            "/shield/shipments/cancelStatus",
            "/shield/getAllUsers"

    ));
}
