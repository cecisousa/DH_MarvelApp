package com.example.dh_marvelapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dh_marvelapp.R;
import com.example.dh_marvelapp.model.pojos.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetalheImagemActivity extends AppCompatActivity {

    private ImageView imagem;
    private FloatingActionButton btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_imagem);

        imagem = findViewById(R.id.imgDetalhe);
        btnVoltar = findViewById(R.id.btnVoltar);

        Result result = getIntent().getParcelableExtra("Result");
        Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imagem);

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalheImagemActivity.this, DetalheActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("Result", result);
            intent.putExtras(bundle);
            startActivity(intent);
        });


    }


}
