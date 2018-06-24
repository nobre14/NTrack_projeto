package com.example.nobre.ntrack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nobre.ntrack.DAO.ConfiguracaoFirebase;
import com.example.nobre.ntrack.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogin;
    private TextView txtCadastrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtCadastrar = (TextView) findViewById(R.id.link_cadastro);

        if (usuarioLogado()) {
            Intent intentVaiParaOMain = new Intent(this, MainActivity.class);
            startActivity(intentVaiParaOMain);
        } else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {
                        usuario = new Usuario();
                        usuario.setEmail(edtEmail.getText().toString());
                        usuario.setSenha(edtSenha.getText().toString());

                        validarLogin();
                    } else {
                        Snackbar snackbar = Snackbar.make(v, "Preencha campo e-mail e senha!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            });

            txtCadastrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, CadastroUsuario.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail().toString(), usuario.getSenha().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login Realizado com sucesso!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public Boolean usuarioLogado() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return true;
        } else return false;
    }
}
