package com.example.nobre.ntrack;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nobre.ntrack.DAO.MotoDAO;
import com.example.nobre.ntrack.helper.CadastroMotoHelper;
import com.example.nobre.ntrack.modelo.Moto;

public class FormularioCadastroMotoActivity extends AppCompatActivity {

    private CadastroMotoHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro_moto);

        helper = new CadastroMotoHelper(FormularioCadastroMotoActivity.this);
        Spinner spinner = (Spinner)findViewById(R.id.spnMarcaMoto);
        String[] marcas = {"Marca", "Suzuki", "Yamaha", "Honda", "Kawazaki"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marcas);
        spinner.setAdapter(adapter);

        Intent intent = getIntent(); // pega os dados que vem da lista
        Moto moto = (Moto)intent.getSerializableExtra("moto");
        if(moto != null){
            helper.preencheFormulario(moto);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnSalvar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FormularioCadastroMotoActivity.this, "Moto salva!", Toast.LENGTH_SHORT).show();
                Moto moto = helper.pegaMoto();
                MotoDAO dao = new MotoDAO(FormularioCadastroMotoActivity.this);
                if(moto.getId() != null){
                    dao.altera(moto);
                }else {
                    dao.insert(moto);
                }
                dao.close();
                Intent intent = new Intent(FormularioCadastroMotoActivity.this, ListaMotoActivity.class);
                startActivity(intent);
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
}
