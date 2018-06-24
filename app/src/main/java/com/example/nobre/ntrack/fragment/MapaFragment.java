package com.example.nobre.ntrack.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.example.nobre.ntrack.modelo.Autodromo;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng posicaoFaculdade = pegaCoordenada("R. Cel. Vicente, 281 - Centro Histórico, Porto Alegre - RS, 90030-041");
        if(posicaoFaculdade != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoFaculdade, 8);
            googleMap.moveCamera(update);
        }

        List<Autodromo> listaAutodromo = Arrays.asList(new Autodromo("AGA/Guaporé", "Guaporé", "RS", "3.080m", "http://autodromodeguapore.com.br"),
                new Autodromo("Velopark", "Santa Rita", "RS", "2.500m", "http://velopark.com.br"),
                new Autodromo("Tarumã", "Viamão", "RS","3.039m", "http://www.autodromodetaruma.com.br"));

        for(Autodromo autodromo : listaAutodromo){
            LatLng coordenada = pegaCoordenada(autodromo.getCidade()+ "-" + autodromo.getEstado());
            if(coordenada != null){
                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(autodromo.getNome());
                marcador.snippet(autodromo.getSite());
                googleMap.addMarker(marcador);
            }
        }
    }

    private LatLng pegaCoordenada(String endereco){

        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco,
                            1);
            if(!resultados.isEmpty()){
                LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
