package com.example.dh_marvelapp.model.repository;

import com.example.dh_marvelapp.model.pojos.Comics;

import io.reactivex.Observable;

import static com.example.dh_marvelapp.model.data.remote.RetrofitService.getApiService;

public class MarvelRepository {

    public Observable<Comics> getComics(String format, String formatType, Boolean noVariants,String title, String orderBy, Integer limit, String ts, String hash,String apiKey) {
        return getApiService().getAllComics(format, formatType, noVariants, title, orderBy, limit, ts, hash, apiKey);
    }
}



