package com.example.nobre.ntrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nobre.ntrack.DAO.MotoDAO;
import com.example.nobre.ntrack.asyncTask.MarcasTask;
import com.example.nobre.ntrack.fragment.PromptDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.capa_main_activity) ImageView capa;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        autenticacao = FirebaseAuth.getInstance();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        MotoDAO motoDAO = new MotoDAO(this);
        if(motoDAO.buscarMarcas().size() < 4) {
            MarcasTask marcasTask = new MarcasTask(this);
            marcasTask.execute();
        }
        motoDAO.close();
        Picasso.get().load("https://github.com/nobre14/Ntrack/blob/master/OChacal%C2%AEMK3_0066_Rafael_Nobre.jpg?raw=true").into(capa);
    }

    @Override
    public void onBackPressed() { ;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dialog_fragment_item){
            mostraDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.cadastrar_moto_menu) {
            Intent vaiParaOForumlarioCadastroMoto = new Intent(this, FormularioCadastroMotoActivity.class);
            startActivity(vaiParaOForumlarioCadastroMoto);
        } else if(id == R.id.lista_moto) {
            Intent intent = new Intent(this, ListaMotoActivity.class);
            startActivity(intent);
        } else if (id == R.id.autodromos_menu) {
            Intent vaiParaAListaAutodromo = new Intent(this, ListaAutodromoActivity.class);
            startActivity(vaiParaAListaAutodromo);
        } else if (id == R.id.cronometragem_menu) {
            Toast.makeText(this, "Clicou no Cronometragem", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.configuracoes_menu) {
            Toast.makeText(this, "Clicou no Configurações", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.sair_menu) {
            deslogarUsuario();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Clicou no Compartilhar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Clicou no Enviar", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mostraDialog(){
        PromptDialogFragment dialog = new PromptDialogFragment();
        dialog.setCancelable(true);
        dialog.show(getSupportFragmentManager(), "tag");
    }

    private void deslogarUsuario(){
        autenticacao.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
