package com.example.nobre.ntrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nobre.ntrack.DAO.MotoDAO;
import com.example.nobre.ntrack.modelo.Moto;

import java.util.List;

public class ListaMotoActivity extends AppCompatActivity {

    private ListView listaMotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_moto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaMotos = (ListView) findViewById(R.id.lista_motos);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaMotoActivity.this, FormularioCadastroMotoActivity.class);
                startActivity(intent);
            }
        });


        listaMotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Moto moto = (Moto) listaMotos.getItemAtPosition(position);
                Intent intentDetalhemoto = new Intent(ListaMotoActivity.this, DetalheMotoActivity.class);
                intentDetalhemoto.putExtra("moto", moto);
                startActivity(intentDetalhemoto);
            }
        });

        registerForContextMenu(listaMotos);
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

    private void carregaListaMotos() {
        MotoDAO dao = new MotoDAO(this);
        List<Moto> motos = dao.buscaMotos();
        dao.close();
        ArrayAdapter<Moto> adapter = new ArrayAdapter<Moto>(this, android.R.layout.simple_list_item_1, motos);
        listaMotos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaListaMotos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        MenuItem editar = menu.add("Editar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Moto moto = (Moto) listaMotos.getItemAtPosition(info.position);
                MotoDAO dao = new MotoDAO(ListaMotoActivity.this);
                dao.deleta(moto);
                dao.close();
                Toast.makeText(ListaMotoActivity.this, "Moto " + moto.getMarca() + " " +
                        moto.getModelo() + " excluida ", Toast.LENGTH_SHORT).show();
                carregaListaMotos();
                return false;
            }
        });

        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Moto moto = (Moto) listaMotos.getItemAtPosition(info.position);
                Intent intent = new Intent(ListaMotoActivity.this, FormularioCadastroMotoActivity.class);
                intent.putExtra("moto", moto);
                startActivity(intent);
                return false;
            }
        });
    }

}
