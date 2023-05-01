package com.example.messenger.Fragments;

import com.example.messenger.Notifications.MyResponsive;
import com.example.messenger.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
            "Content-Type:application/json",
            "Authorization:key=AAAApHfnEWw:APA91bFk-HyJHBXBZcpk-U8Mbn8dDS9A-F4oHW-UkTSDx1bJD3vVF1lkLfCLvIDdMNVYomTMt4UwdcjHGMy_94gOGoRrWBnUYD_TwbrP-LK9FDCnvU-JfmkVcwLYvMZIS0WJVbQZDEBG"
            }
     )

    @POST("fcm/send")
    Call<MyResponsive> sendNotification(@Body Sender body);
}
