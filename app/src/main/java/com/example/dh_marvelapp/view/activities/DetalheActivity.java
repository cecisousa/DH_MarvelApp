package com.example.dh_marvelapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh_marvelapp.R;
import com.example.dh_marvelapp.model.pojos.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imgHQ;
    private TextView txtTitulo;
    private TextView txtDescricao;
    private TextView txtData;
    private TextView txtValor;
    private TextView txtPaginas;
    private FloatingActionButton btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        initViews();

        if (getIntent() != null) {
            Result result = getIntent().getParcelableExtra("Result");
//            Picasso.get().load(result.getThumbnail().getPath() + "jpg").into(imgHQ);
            txtTitulo.setText(result.getTitle());
            txtDescricao.setText(result.getDescription());
//            txtData.setText(result.getDates());
//            txtValor.setText(result.getPrices());
//            txtPaginas.setText(result.getPageCount());
        }

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalheActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

    public void initViews() {
        imgHQ = findViewById(R.id.imgHQ);
        txtTitulo= findViewById(R.id.txtTitulo);
        txtDescricao= findViewById(R.id.txtDescricao);
        txtData= findViewById(R.id.txtData);
        txtValor= findViewById(R.id.txtValor);
        txtPaginas= findViewById(R.id.txtPaginas);
        btnVoltar= findViewById(R.id.floatingActionButton);
    }
}
