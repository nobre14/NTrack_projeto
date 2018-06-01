package com.example.nobre.ntrack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nobre.ntrack.ListaAutodromoActivity;
import com.example.nobre.ntrack.R;
import com.example.nobre.ntrack.modelo.Autodromo;

import java.util.Arrays;
import java.util.List;


public class ListaAutodromoFragment extends Fragment {


    public ListaAutodromoFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_autodromo, container, false);

        List<Autodromo> listaAutodromo = Arrays.asList(new Autodromo("AGA/Guaporé", "Guaporé", "RS", "3.080m", "http://autodromodeguapore.com.br"),
                new Autodromo("Velopark", "Santa Rita", "RS", "2.500m", "http://velopark.com.br"),
                new Autodromo("Tarumã", "Viamão", "RS","3.039m", "http://www.autodromodetaruma.com.br"));

        ArrayAdapter<Autodromo> adapterAutodromo = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listaAutodromo);

        ListView campoListaAutodromo = (ListView)view.findViewById(R.id.lista_autodromos_fragment_frame_principal);
        campoListaAutodromo.setAdapter(adapterAutodromo);

        campoListaAutodromo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Autodromo autodromo = (Autodromo) parent.getItemAtPosition(position);
                ListaAutodromoActivity listaAutodromoActivity = (ListaAutodromoActivity) getActivity();
                listaAutodromoActivity.selecionaAutodromo(autodromo);
            }
        });
        return view;
    }

}
