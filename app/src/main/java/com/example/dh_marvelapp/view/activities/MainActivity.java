package com.example.dh_marvelapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.dh_marvelapp.R;
import com.example.dh_marvelapp.model.pojos.Date;
import com.example.dh_marvelapp.model.pojos.Result;
import com.example.dh_marvelapp.view.adapter.MarvelRecyclerViewAdapter;
import com.example.dh_marvelapp.view.interfaces.OnClick;
import com.example.dh_marvelapp.viewmodel.MarvelViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.dh_marvelapp.model.data.util.AppUtil.md5;

public class MainActivity extends AppCompatActivity implements OnClick {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MarvelViewModel viewModel;
    private List<Result> listaResults = new ArrayList<>();
    private MarvelRecyclerViewAdapter adapter;
    public static final String PUBLIC_API_KEY = "07d51416d59a89ee5cd79f7f4308bc4a";
    public static final String PRIVATE_API_KEY = "21d520301d5067c31602703029a12ceeabeaa33e";
    private String format = "comic";
    private String formatType = "comic";
    private Boolean noVariants = true;
    private String title = "amazing%20spider-man";
    private String orderBy = "-title";
    private Integer limit = 50;
    private String ts;
    private String hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getTimeStamp();
        getHash();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        viewModel.getAllComics(format, formatType, noVariants, title, orderBy, limit, ts, hash, PUBLIC_API_KEY);

        viewModel.getListaComics().observe(this, resultadoLista -> {
            adapter.atualizaLista(resultadoLista);
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void getTimeStamp() {
        java.util.Date date = new java.util.Date();
        long ts = date.getTime();
    }

    public void getHash() {
        java.util.Date date = new java.util.Date();
        long ts = date.getTime();
        String hash = md5(ts + "21d520301d5067c31602703029a12ceeabeaa33e" + "07d51416d59a89ee5cd79f7f4308bc4a");
    }

    public void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        viewModel = ViewModelProviders.of(this).get(MarvelViewModel.class);
        adapter = new MarvelRecyclerViewAdapter(listaResults, this);
    }

    @Override
    public void click(Result result) {
        Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Result", result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
