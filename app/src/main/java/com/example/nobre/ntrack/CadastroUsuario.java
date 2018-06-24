package com.example.nobre.ntrack;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nobre.ntrack.DAO.ConfiguracaoFirebase;
import com.example.nobre.ntrack.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroUsuario extends AppCompatActivity {

    @BindView(R.id.edtCadEmail)
    EditText cadEmail;
    @BindView(R.id.edtCadSenha1)
    EditText cadSenha1;
    @BindView(R.id.edtCadSenha2)
    EditText cadSenha2;
    @BindView(R.id.edtCadNome)
    EditText cadNome;
    @BindView(R.id.btnCadastrar)
    Button cadCadastrar;
    @BindView(R.id.btnCancelar)
    Button cadCancelar;

    private FirebaseAuth autenticacao;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        ButterKnife.bind(this);

        cadCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cadSenha1.getText().toString().equals(cadSenha2.getText().toString())) {
                    usuario = new Usuario();
                    usuario.setEmail(cadEmail.getText().toString());
                    usuario.setSenha(cadSenha1.getText().toString());
                    usuario.setNome(cadNome.getText().toString());

                    cadastrarUsuario();
                } else {
                    Snackbar snackbar = Snackbar.make(v, "As senhas não conferem!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        cadCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroUsuario.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            insereUsuario(usuario);
                        } else {
                            String erroExcecao = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                erroExcecao = "Senha fraca, a senha deve conter no mínimo 8 caracteres que contenham letras e números.";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                erroExcecao = "O e-mail digitado é invalido, digite novo e-mail.";
                            } catch (FirebaseAuthUserCollisionException e) {
                                erroExcecao = "Este e-mail já está cadastrado!";
                            } catch (Exception e) {
                                erroExcecao = "Erro ao efetuar o cadastro";
                                e.printStackTrace();
                            }
                            Toast.makeText(CadastroUsuario.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private boolean insereUsuario(Usuario usuario) {

        try{
            reference = ConfiguracaoFirebase.getFirebase().child("usuarios"); // defino o nó (equivalente a tabela)
            reference.push().setValue(usuario); // valor abaixo do push(PK), insere o objeto no banco
            Toast.makeText(CadastroUsuario.this, "Usuário salvo com sucesso!!", Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            Toast.makeText(CadastroUsuario.this, "Erro ao salvar usuário!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return false;
        }
    }
}
