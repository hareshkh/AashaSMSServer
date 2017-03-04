package com.example.haresh.aashasmsserver.retrofit;

import com.example.haresh.aashasmsserver.models.AppointmentModel;
import com.example.haresh.aashasmsserver.models.DoctorModel;
import com.example.haresh.aashasmsserver.models.HospitalModel;
import com.example.haresh.aashasmsserver.models.PatientModel;
import com.example.haresh.aashasmsserver.models.VisitingDoctorModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiCalls {
    String BASE_URL = "https://aasha-cfd.herokuapp.com/db/";

    @GET("hospitals")
    Call<List<HospitalModel>> getHospitals();

    @GET("appointments")
    Call<List<AppointmentModel>> getAppointments();

    @GET("doctors")
    Call<List<DoctorModel>> getDoctors();

    @GET("visits")
    Call<List<VisitingDoctorModel>> getVisits();

    @FormUrlEncoded
    @POST("login")
    Call<Integer> loginRequest(@Field("uid") long uid, @Field("password") String password);

    @FormUrlEncoded
    @POST("patients")
    Call<PatientModel> signupRequest(@Field("name") String name,
                                     @Field("uid") long uid,
                                     @Field("password") String password,
                                     @Field("image") String image,
                                     @Field("address") String address,
                                     @Field("phone") String phone,
                                     @Field("pregnant") int isPregnant,
                                     @Field("duedate") String dueDate,
                                     @Field("conceivedate") String conceiveDate);

    @FormUrlEncoded
    @PATCH("patients/{patient_id}")
    Call<PatientModel> setHospitalId(@Path("patient_id") int patient_id, @Field("hospital_id") int hospitalID);

    @FormUrlEncoded
    @POST("appointments")
    Call<AppointmentModel> appointmentBookRequest(@Field("patient_id") int patientId,
                                                  @Field("hospital_id") int hospitalId,
                                                  @Field("doctor_id") int doctorId,
                                                  @Field("time") String time,
                                                  @Field("description") String description,
                                                  @Field("status") String status);

    class Factory {
        public static ApiCalls service;

//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        public static ApiCalls getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.
                        create()).baseUrl(BASE_URL).build();
                service = retrofit.create(ApiCalls.class);
            }
            return service;
        }
    }
}
