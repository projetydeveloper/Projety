package com.projety.api;

import android.util.Log;

import com.projety.model.Parties;
import com.projety.model.Party;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Djeme Mahamat on 13/06/2015.
 */
public class ApiClient {

    private static ProjetyApiInterface projetyApiService;
    private static final String API_ENDPOINT ="http://ec2-52-28-22-45.eu-central-1.compute.amazonaws.com:8080/api/";

            //"http://192.168.1.27:8080/api/";

    //http://ec2-52-28-22-45.eu-central-1.compute.amazonaws.com:8080/api/parties?offset=0&limit=20


    public static ProjetyApiInterface getProjetyApiClient() {
        if (projetyApiService == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_ENDPOINT)
                    .build();

            projetyApiService = restAdapter.create(ProjetyApiInterface.class);

        }

        return projetyApiService;
    }

    public interface ProjetyApiInterface {
        @GET("/parties")
        //void getParties(Callback<List<Party>> callback);
        void getParties(@Query("limit") int limit, @Query("offset") int offset, Callback<Parties> callback);
    }
}
