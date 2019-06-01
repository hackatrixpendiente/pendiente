package com.example.pendienteapp.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {

    @POST("empresa/producto")
    @FormUrlEncoded
    Observable<String> dataempresa(@Field("idSede") Integer idSede);

    @POST("clase")
    @FormUrlEncoded
    Observable<String> claseConfig(@Field("idusuario") Integer idusuario,
                                   @Field("flgencendido") Integer flgencendido);

    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password);
}
