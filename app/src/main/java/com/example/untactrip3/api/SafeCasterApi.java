package com.example.untactrip3.api;
import com.example.untactrip3.SiMenu;
import com.example.untactrip3.dto.FlowDensity_dong;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SafeCasterApi {

    @Headers("Content-Type: application/json")
    @GET("https://apis.openapi.sk.com/safecaster/v1/search/safetyindex/ldongcd/flow")
    Call<FlowDensity_dong> getFlowDensity_dong(@Header("appKey") String appKey, @Query("filterDate") String filterDate, @Query("ldongCd") String ldongCd);

    @Headers("Content-Type: application/json")
    @GET("https://apis.openapi.sk.com/safecaster/v1/search/safetyindex/ldongcd/flow")
    Call<FlowDensity_dong> getSiMenu(@Header("appKey") String appKey, @Query("filterDate") String filterDate, @Query("ldongCd") String ldongCd);

    @Headers("Content-Type: application/json")
    @GET("/apps/socialcontact/ldongcd/timeseries/three")
    Call<Object> getPredData(@Query("ldongCd") String lDongCd);

    @Headers("Content-Type: application/json")
    @GET("/api/user/tid-app")
    Call<Object> getTidLoginSecret(@Query("nonce") String nonce, @Query("type") String type);

    @Headers("Content-Type: application/json")
    @GET("/api/user/tid/result")
    Call<Object> getTidResult(@Query("idToken") String idToken);
}
