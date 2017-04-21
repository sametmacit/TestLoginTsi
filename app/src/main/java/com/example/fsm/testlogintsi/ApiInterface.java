package com.example.fsm.testlogintsi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fsm on 21.04.2017.
 */

public interface ApiInterface {

  //  @Post("api/contacts/{name}/{surname}/phone")
 //   Call<Login> authenticate(@Path("name") String name, @Path("surname") String surname,@Path("phone") String phone);

    @POST("api/contacts")
    Call<User> createUser(@Body User user);
}
