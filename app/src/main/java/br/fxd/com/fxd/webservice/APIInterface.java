package br.fxd.com.fxd.webservice;


import android.app.Service;

import java.util.ArrayList;
import java.util.List;

import br.fxd.com.fxd.model.Occurrence;
import br.fxd.com.fxd.model.User;
import br.fxd.com.fxd.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by matheuscatossi on 01/07/17.
 */

public interface APIInterface {

    public static final String INSERT_USER = "insertuser.php";
    public static final String UPDATE_LOCATION = "updatelocation.php";
    public static final String INSERT_OCCURRENCE = "insertoccurrence.php";
    public static final String HAPPN_FXD = "happnfxd.php";
    public static final String OCCURRENCE = "occurrence.php";


    @GET(Constants.HAPPN_FXD)
    Call<List<User>> getHappnFXD();

    @GET(Constants.OCCURRENCE)
    Call<ArrayList<Occurrence>> getOccurrence();

    @POST(Constants.INSERT_USER)
    Call<Object> insertUser(@Body User user);

    @POST(Constants.UPDATE_LOCATION)
    Call<Object> updateLocation(@Body User user);

    @FormUrlEncoded
    @POST(Constants.INSERT_OCCURRENCE)
    Call<Object> insertOccurrence(@Field("type") String type, @Field("lat") String lat, @Field("lng") String lng);

}