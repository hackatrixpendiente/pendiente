package com.example.pendienteapp.Retrofit;

import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
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


    @POST("pendiente/registro")
    @FormUrlEncoded
    Observable<String> registroCompra(@Field("cantidad") Integer cantidad,
                                   @Field("mensaje") String mensaje,
                                   @Field("idProducto") Integer idProducto,
                                   @Field("idUsuario") Integer idUsuario);
}
