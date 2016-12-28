package com.example.ananpengkhun.myprojectfinal.httpmanager.httpinterface;

import com.example.ananpengkhun.myprojectfinal.dao.DataDao;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ananpengkhun on 12/28/16.
 */

public interface DataInterface {

    @GET("pressure")
    Call<DataDao> listRepos();
}
