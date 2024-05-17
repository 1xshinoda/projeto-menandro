package com.example.projetomenandro.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomenandro.Modelos.Usuario;
import com.example.projetomenandro.R;
import com.example.projetomenandro.Util.configuraBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class cadastro_activity extends AppCompatActivity {
    Usuario usuario;
    FirebaseAuth autenticacao;

    EditText CampoNome, CampoEmail, CampoSenha, CampoTel;
    Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        setContentView(R.layout.cadastro_main);
        inicializar();
    }

    private void inicializar() {
        CampoNome = findViewById(R.id.EditEmailLogin);
        CampoEmail = findViewById(R.id.EditTextEmail);
        CampoSenha = findViewById(R.id.EditLoginSenha);
        CampoTel = findViewById(R.id.EditTextTel);
        botaoCadastrar = findViewById(R.id.BTcadastrar);

    }

    public void validarcampos(View v) {
        String nome = CampoNome.getText().toString();
        String email = CampoEmail.getText().toString();
        String Senha = CampoSenha.getText().toString();
        String Tel = CampoTel.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!Senha.isEmpty()) {
                    if (!Tel.isEmpty()) {
                        usuario = new Usuario();
                        usuario.setNome(nome);
                        usuario.setEmail(email);
                        usuario.setSenha(Senha);


                        CadastrarUsuario();

                    } else {
                        Toast.makeText(this, "Preencha o Telefone!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Preencha a Senha!", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "Preencha o Email!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o Nome!", Toast.LENGTH_SHORT).show();
        }

    }

    private void CadastrarUsuario() {
        autenticacao = configuraBD.fireauth();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()

        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(cadastro_activity.this, "Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    String excecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte";

                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao =" Digite Um Email Valido!";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "A conta Ja existe!";
                    } catch (Exception e){
                        excecao = "Erro ao cadastrar Usuario"+e.getMessage();
                        e.printStackTrace();

                    }
                    Toast.makeText(cadastro_activity.this,excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
