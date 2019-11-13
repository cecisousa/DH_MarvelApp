package com.example.dh_marvelapp.model.data.remote;

import com.example.dh_marvelapp.model.pojos.Comics;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelAPI {

    @GET("comics")
    Observable<Comics> getAllComics(@Query("format") String format,
                                    @Query("formatType") String formatType,
                                    @Query("noVarints") Boolean noVariants,
                                    @Query("title") String title,
                                    @Query("orderBy") String orderBy,
                                    @Query("limit") Integer limit,
                                    @Query("ts") String ts,
                                    @Query("hash") String hash,
                                    @Query("apiKey") String apiKey);

}
