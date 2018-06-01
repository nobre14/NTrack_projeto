package com.example.nobre.ntrack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nobre.ntrack.R;
import com.example.nobre.ntrack.modelo.Autodromo;

public class DetalheAutodromoFragment extends Fragment {

    private TextView campoNome;
    private TextView campoCidade;
    private TextView campoEstado;
    private TextView campoPercurso;
    private TextView campoSite;
    private Autodromo autodromo;
    public DetalheAutodromoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detalhe_autodromo, container, false);

        campoNome = view.findViewById(R.id.titulo_nome_detalhe_autodromo);
        campoCidade = view.findViewById(R.id.cidade_detalhe_autodromo);
        campoEstado = view.findViewById(R.id.estado_detalhe_autodromo);
        campoPercurso = view.findViewById(R.id.percurso_detalhe_autodromo);
        campoSite = view.findViewById(R.id.site_detalhe_autodromo);

        Bundle parametros = getArguments();
        if(parametros != null){
            autodromo = (Autodromo) parametros.getSerializable("autodromo");
            populaCampos(autodromo);
        }

        Button botaoAvalia = view.findViewById(R.id.avalia_autodromo);
        botaoAvalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Avaliação enviada", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public void populaCampos(Autodromo autodromo) {
        campoNome.setText(autodromo.getNome());
        campoCidade.setText(autodromo.getCidade());
        campoEstado.setText(autodromo.getEstado());
        campoPercurso.setText(autodromo.getPercurso());
        campoSite.setText(autodromo.getSite());
    }
}
