package com.example.nobre.ntrack;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nobre.ntrack.modelo.Moto;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalheMotoActivity extends AppCompatActivity {

    @BindView(R.id.txtMarcaDetalheMoto) TextView marca;
    @BindView(R.id.txtModeloDetalheMoto) TextView modelo;
    @BindView(R.id.txtAnoDetalheMoto) TextView ano;
    @BindView(R.id.txtCilindradaDetalheMoto) TextView cilindrada;
    @BindView(R.id.btnCompartilharDetalhes) FloatingActionButton botaoCompartilha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_moto);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        final Moto moto = (Moto) intent.getSerializableExtra("moto");

        marca.setText(moto.getMarca());
        modelo.setText(moto.getModelo());
        ano.setText(String.valueOf(moto.getAno()));
        cilindrada.setText(String.valueOf(moto.getCilndrada()));

        botaoCompartilha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .putExtra(Intent.EXTRA_TEXT, "Dados da Motocicleta:       " +
                                " Modelo: " + moto.getModelo() + ", Marca: " + moto.getMarca()+ " Cilindrada: " +
                                moto.getCilndrada()+ ", Ano: " + moto.getAno() + ".")
                        .setType("text/plain");
                dispararIntent(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhe_moto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_retorna_principal:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dispararIntent(Intent intent){
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Toast.makeText(this, "Erro", Toast.LENGTH_SHORT).show();
        }
    }
}
