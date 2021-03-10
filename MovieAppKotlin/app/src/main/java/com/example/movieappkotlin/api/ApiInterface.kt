package com.example.movieappkotlin.api

import com.example.movieappkotlin.model.LoginResponseModel
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET ("login")
    fun login(@Query("email") email: String, @Query("password") password: String): Single<LoginResponseModel>

    @POST("register")
    fun regis(@Field("name") name: String, @Field("email") email: String, @Field("password") password: String, @Field("address") address: String, @Field("dob") dob: String): Single<LoginResponseModel>
}