package com.example.haresh.aashasmsserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Base64;
import android.util.Log;

import com.example.haresh.aashasmsserver.models.AppointmentModel;
import com.example.haresh.aashasmsserver.models.DoctorModel;
import com.example.haresh.aashasmsserver.models.HospitalModel;
import com.example.haresh.aashasmsserver.models.PatientModel;
import com.example.haresh.aashasmsserver.models.VisitingDoctorModel;
import com.example.haresh.aashasmsserver.retrofit.ApiCalls;
import com.google.gson.Gson;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsHandler extends BroadcastReceiver {

    final static String TAG = "SMS_Handler";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Message recieved");
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdus = (Object[]) bundle.get("pdus");
                assert pdus != null;
                String finalMessage = "";
                String senderNum = "";
                for (Object aPdusObj : pdus) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);

                    senderNum = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    finalMessage = finalMessage.concat(message);
                }

                List<String> params = Arrays.asList(finalMessage.split("\\s*,\\s*"));
                final int requestCode = Integer.parseInt(params.get(0));

                switch (requestCode) {
                    case 1: {
                        final String finalSenderNum = senderNum;
                        ApiCalls.Factory.getInstance().getHospitals().enqueue(new Callback<List<HospitalModel>>() {
                            @Override
                            public void onResponse(Call<List<HospitalModel>> call, Response<List<HospitalModel>> response) {
                                List<HospitalModel> hospitals = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(hospitals, new TypeToken<List<HospitalModel>>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg,finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<List<HospitalModel>> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 2: {
                        final int patientId = Integer.parseInt(params.get(1));
                        final String finalSenderNum = senderNum;
                        ApiCalls.Factory.getInstance().getAppointments().enqueue(new Callback<List<AppointmentModel>>() {
                            @Override
                            public void onResponse(Call<List<AppointmentModel>> call, Response<List<AppointmentModel>> response) {
                                List<AppointmentModel> appointments = new ArrayList<AppointmentModel>();
                                for (AppointmentModel appointmentModel : response.body()) {
                                    if (appointmentModel.getPatientId() == patientId) {
                                        appointments.add(appointmentModel);
                                    }
                                }
                                Gson gson = new Gson();
                                String json = gson.toJson(appointments, new TypeToken<List<AppointmentModel>>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg, finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<List<AppointmentModel>> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 3: {
                        final String finalSenderNum = senderNum;
                        ApiCalls.Factory.getInstance().getDoctors().enqueue(new Callback<List<DoctorModel>>() {
                            @Override
                            public void onResponse(Call<List<DoctorModel>> call, Response<List<DoctorModel>> response) {
                                List<DoctorModel> doctors = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(doctors, new TypeToken<List<DoctorModel>>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg,finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<List<DoctorModel>> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 4: {
                        final String finalSenderNum = senderNum;
                        ApiCalls.Factory.getInstance().getVisits().enqueue(new Callback<List<VisitingDoctorModel>>() {
                            @Override
                            public void onResponse(Call<List<VisitingDoctorModel>> call, Response<List<VisitingDoctorModel>> response) {
                                List<VisitingDoctorModel> visits = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(visits, new TypeToken<List<VisitingDoctorModel>>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg,finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<List<VisitingDoctorModel>> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 5: {
                        final String finalSenderNum = senderNum;
                        final long uid = Long.parseLong(params.get(1));
                        final String password = params.get(2);
                        ApiCalls.Factory.getInstance().loginRequest(uid, password).enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                byte[] data = new byte[0];
                                try {
                                    data = String.valueOf(response.body()).getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg,finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 6: {
                        final String finalSenderNum = senderNum;
                        final String name = params.get(1);
                        final long uid = Long.parseLong(params.get(2));
                        final String password = params.get(3);
                        final String image = params.get(4);
                        final String address = params.get(5);
                        final String phone = params.get(6);
                        final int isPregnant = Integer.parseInt(params.get(7));
                        final String dueDate = params.get(8);
                        final String conceiveDate = params.get(9);
                        ApiCalls.Factory.getInstance().signupRequest(name, uid, password, image, address,
                                phone, isPregnant, dueDate, conceiveDate).enqueue(new Callback<PatientModel>() {
                            @Override
                            public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                                PatientModel patient = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(patient, new TypeToken<PatientModel>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg,finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<PatientModel> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 7: {
                        final String finalSenderNum = senderNum;
                        final int pid = Integer.parseInt(params.get(1));
                        final int hid = Integer.parseInt(params.get(2));
                        ApiCalls.Factory.getInstance().setHospitalId(pid, hid).enqueue(new Callback<PatientModel>() {
                            @Override
                            public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                                PatientModel patient = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(patient, new TypeToken<PatientModel>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg,finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<PatientModel> call, Throwable t) {

                            }
                        });
                        break;
                    }
                    case 8: {
                        final String finalSenderNum = senderNum;
                        final int pid = Integer.parseInt(params.get(1));
                        final int hid = Integer.parseInt(params.get(2));
                        final int did = Integer.parseInt(params.get(3));
                        final String time = params.get(4);
                        final String description = params.get(5);
                        final String status = params.get(6);
                        ApiCalls.Factory.getInstance().appointmentBookRequest(pid, hid, did, time, description, status).enqueue(new Callback<AppointmentModel>() {
                            @Override
                            public void onResponse(Call<AppointmentModel> call, Response<AppointmentModel> response) {
                                AppointmentModel appointment = response.body();
                                Gson gson = new Gson();
                                String json = gson.toJson(appointment, new TypeToken<AppointmentModel>(){}.getType());
                                byte[] data = new byte[0];
                                try {
                                    data = json.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                String encoded = Base64.encodeToString(data, Base64.DEFAULT);
                                String responseMsg = requestCode + "," + encoded;
                                sendSms(responseMsg, finalSenderNum);
                            }

                            @Override
                            public void onFailure(Call<AppointmentModel> call, Throwable t) {

                            }
                        });
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e.getMessage());
        }
    }

    public static void sendSms(String msg, String phoneNo) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> parts = smsManager.divideMessage(msg);
            if(parts.size() > 1){
                smsManager.sendMultipartTextMessage(phoneNo, null, parts, null, null);
            } else {
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            }
            Log.d(TAG, "Sms Sent");
        } catch (Exception e) {
            Log.d(TAG, "Sms Not Sent");
        }
    }
}