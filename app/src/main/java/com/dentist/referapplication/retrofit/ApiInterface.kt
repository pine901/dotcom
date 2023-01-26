package com.my.dronlineappointment.retrofit

import com.google.gson.JsonElement
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @Multipart
    @POST("auth/register")
    fun registerUser(
        @Query("fullname") fullname: String,
        @Query("password") password: String,
        @Query("email") email: String,
        @Query("phoneno") phoneno: String,
        @Query("gender") gender: String,
        @Query("fulladdress") fulladdress: String,
        @Query("country") country: String,
        @Query("pincode") pincode: String,
        @Query("device_token") device_token: String,
        @Query("device_type") device_type: String,
        @Query("DOB") DOB: String,
        @Query("Reason") Reason: String,
        @Part image: MultipartBody.Part,
        @Part proofImage: List<MultipartBody.Part>
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("password/email")
    fun forgotPassword(
        @Field("email") email: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: String
    ): Call<JsonElement>

    @GET("auth/homedata")
    fun homeData(): Call<JsonElement>

    @GET("auth/user-profile")
    fun userProfile(): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/search")
    fun doctorList(@Field("search") search: String): Call<JsonElement>


    @Multipart
    @POST("auth/profile/update")
    fun updateUserProfile(
        @Query("fullname") fullname: String,
        @Query("pincode") pin: String,
        @Query("fulladdress") fulladdress: String,
        @Query("phoneno") phoneno: String,
        @Part image: MultipartBody.Part
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/doctor-slots")
    fun doctorSlots(
        @Field("doctor_id") doc_id: String,
        @Field("date") date: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/doctor-detail")
    fun doctorDetail(
        @Field("doctor_id") doc_id: String
    ): Call<JsonElement>


    @GET("auth/booking_confirmation_amount")
    fun bookingAmount(): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/create-customer")
    fun saveCardDetail(
        @Field("number") number: String,
        @Field("exp_month") month: String,
        @Field("exp_year") year: String,
        @Field("cvc") cvv: String,
        @Field("email") email: String,
        @Field("user_id") id: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/charge-customer")
    fun appFees(
        @Field("amount") amount: String,
        @Field("doctor_id") doc_id: String,
        @Field("user_id") id: String
    ): Call<JsonElement>

    @Multipart
    @POST("auth/create-appointment")
    fun bookAppointment(
        @Query("patient_name") patient_name: String,
        @Query("phoneno") phoneno: String,
        @Query("illness_details") illness_details: String,
        @Query("status") status: String,
        @Query("appointment_for") appointment_for: String,
        @Query("slot_id") slot_id: String,
        @Query("appointment_date") appointment_date: String,
        @Query("doctor_id") doc_id: String,
        @Query("user_id") id: String,
        @Query("priority_status") priority_status: String,
        @Part image: List<MultipartBody.Part>
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/appointment-details")
    fun payDrFees(
        @Field("appointment_id") appointment_id: String,
        @Field("amount") amount: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/upcoming-completed")
    fun appointmentsList(
        @Field("appointment_type") appointment_type: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/cancel-appointment")
    fun cancelAppointment(
        @Field("appointment_id") id: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/reschedule-appointment")
    fun rescheduleAppointment(
        @Field("slot_id") slot_id: String,
        @Field("appointment_id") appointment_id: String,
        @Field("appointment_date") appointment_date: String,
        @Field("doctor_id") doc_id: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/appointment-status-update")
    fun statusUpdate(
        @Field("appointment_id") appointment_id: String,
        @Field("user_id") user_id: String,
        @Field("doctor_id") doctor_id: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/product_lists")
    fun cartList(
        @Field("appointment_id") appointment_id: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/get_shipping_address")
    fun shippingAddress(
        @Field("appointment_id") appointment_id: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/product_payment")
    fun productsPayment(
        @Field("appointment_id") appointment_id: String,
        @Field("amount") amount: String
    ): Call<JsonElement>


    @FormUrlEncoded
    @POST("auth/shipping_address")
    fun addAddress(
        @Field("address_type") address_type: String,
        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("addressline1") addressline1: String,
        @Field("addressline2") addressline2: String,
        @Field("Suburb") Suburb: String,
        @Field("state") state: String,
        @Field("postalcode") postalcode: String,
        @Field("country") country: String
    ): Call<JsonElement>


    @GET("auth/orderhistory")
    fun getOrders(): Call<JsonElement>

    @GET("auth/patient-appointment-notification")
    fun getNotifications(): Call<JsonElement>

    @GET("auth/get-drug-routine")
    fun getDrugRoutine(): Call<JsonElement>

    @GET("auth/product-list-info")
    fun medicineList(): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/add-drug-routine")
    fun adDrugRoutine(
        @Field("medicine_name") medicine_name: String,
        @Field("amount") amount: String,
        @Field("start_date") start_date: String,
        @Field("end_date") end_date: String,
        @Field("time") time: String,
        @Field("eating_time") eating_time: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/update-product-list")
    fun updateCartList(
        @Field("product_list") product_list: String,
        @Field("appointment_id") appointment_id: String
    ): Call<JsonElement>

    @FormUrlEncoded
    @POST("auth/get-fee-type")
    fun feesType(
        @Field("doctor_id") doctor_id: String
    ): Call<JsonElement>

    @POST("auth/logout")
    fun logoutApi(
    ): Call<JsonElement>

}
