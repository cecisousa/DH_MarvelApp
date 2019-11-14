package com.example.dh_marvelapp.model.data.remote;

import com.example.dh_marvelapp.model.pojos.ComicsResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelAPI {

    @GET("comics?")
    Observable<ComicsResult> getAllComics(@Query("format") String format,
                                          @Query("formatType") String formatType,
                                          @Query("noVariants") boolean noVariants,
                                          @Query("orderBy") String orderBy,
                                          @Query("limit") String limit,
                                          @Query("ts") String ts,
                                          @Query("hash") String hash,
                                          @Query("apikey") String apiKey);

}
