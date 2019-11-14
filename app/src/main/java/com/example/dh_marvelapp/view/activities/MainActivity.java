package com.example.dh_marvelapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dh_marvelapp.R;
import com.example.dh_marvelapp.model.pojos.Result;
import com.example.dh_marvelapp.view.adapter.MarvelRecyclerViewAdapter;
import com.example.dh_marvelapp.view.interfaces.OnClick;
import com.example.dh_marvelapp.viewmodel.MarvelViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MarvelViewModel viewModel;
    private List<Result> listaResults = new ArrayList<>();
    private MarvelRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        viewModel.getAllComics();

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
