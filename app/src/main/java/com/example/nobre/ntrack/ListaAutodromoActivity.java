package com.example.nobre.ntrack;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.nobre.ntrack.fragment.DetalheAutodromoFragment;
import com.example.nobre.ntrack.fragment.ListaAutodromoFragment;
import com.example.nobre.ntrack.modelo.Autodromo;

public class ListaAutodromoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autodromo);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_principal, new ListaAutodromoFragment());
        if(testeModoPaisagem()){ // se estiver no modo paisagem ativa os 2 fragments dividindo a tela
            fragmentTransaction.replace(R.id.frame_secudario, new DetalheAutodromoFragment());
        }
        fragmentTransaction.commit();
    }

    private boolean testeModoPaisagem(){
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selecionaAutodromo(Autodromo autodromo){
        FragmentManager manager = getSupportFragmentManager();
        if(!testeModoPaisagem()){
            FragmentTransaction ft = manager.beginTransaction();
            DetalheAutodromoFragment detalheAutodromoFragment = new DetalheAutodromoFragment();
            Bundle parametros = new Bundle(); //Bundle é como se fosse uma caixa onde posso guardar varios objetos
            parametros.putSerializable("autodromo", autodromo);
            detalheAutodromoFragment.setArguments(parametros);

            ft.replace(R.id.frame_principal, detalheAutodromoFragment);
            ft.commit();
        }else{
            DetalheAutodromoFragment detalheAutodromoFragment = (DetalheAutodromoFragment)
                    manager.findFragmentById(R.id.frame_secudario);
            detalheAutodromoFragment.populaCampos(autodromo);
        }
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
}
