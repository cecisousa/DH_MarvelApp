package com.example.dh_marvelapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dh_marvelapp.model.pojos.Result;
import com.example.dh_marvelapp.model.repository.MarvelRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MarvelViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> listaComics = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<String> comicsLiveDataError = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MarvelRepository repository = new MarvelRepository();


    public MarvelViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getListaComics() {
        return this.listaComics;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public LiveData<String> getErrorAlbum(){
        return this.comicsLiveDataError;
    }

    public void getAllComics(String format, String formatType, Boolean noVariants,String title, String orderBy, Integer limit, String ts, String hash,String apiKey) {
        disposable.add(
              repository.getComics(format, formatType, noVariants, title, orderBy, limit, ts, hash, apiKey)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .doOnSubscribe(disposable1 -> loading.setValue(true))
              .doOnTerminate(() -> loading.setValue(false))
              .subscribe(comicsResult -> {
                  listaComics.setValue(comicsResult.getData().getResults());
              }, throwable -> {
                  comicsLiveDataError.setValue(throwable.getMessage());
              })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
