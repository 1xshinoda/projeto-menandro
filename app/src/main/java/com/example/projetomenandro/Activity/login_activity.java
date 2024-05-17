package com.example.projetomenandro.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetomenandro.Activity.cadastro_activity;
import com.example.projetomenandro.Modelos.Usuario;
import com.example.projetomenandro.R;
import com.example.projetomenandro.Util.configuraBD;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class login_activity extends AppCompatActivity {
    EditText CampoEmail,CampoSenha;
    Button botaologin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = configuraBD.fireauth();

        inicializarCampo();
    }

public void validarcampos(View view){
        String email = CampoEmail.getText().toString();
        String senha = CampoSenha.getText().toString();

        if (!email.isEmpty()) {
        if (!senha.isEmpty()){
            Usuario usuario =new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);

            logar(usuario);
        }else {
            Toast.makeText(this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
        }

        } else{

            Toast.makeText(this, "Preencha o Email!", Toast.LENGTH_SHORT).show();
        }


}

    private void logar(Usuario usuario) {
        auth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   abrirMenu();
               }else {
                   String excecao="";
                   try {
                          throw task.getException();
                   }catch (FirebaseAuthInvalidUserException e){
                       excecao = "usuario nao cadastrado!";
                   }catch (FirebaseAuthInvalidCredentialsException e){
                       excecao = "Email ou senha Inválidos!";
                   }catch (Exception e){
                       excecao = "erro ao logar!"+e.getMessage();
                       e.printStackTrace();
                   }
                   Toast.makeText(login_activity.this,excecao, Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

    private void abrirMenu() {
        Intent i = new Intent(login_activity.this,Menu.class);
        startActivity(i);

    }


    public void cadastrar(View v){
        Intent i = new Intent(this, cadastro_activity.class);
        startActivity(i);

        }


@Override
        protected void onStart() {
            super.onStart();
    FirebaseUser usuarioauth =auth.getCurrentUser();
    if(usuarioauth !=null){
        abrirMenu();

    }
        }


        private void inicializarCampo(){
         CampoEmail =findViewById(R.id.EditEmailLogin);
         CampoSenha = findViewById(R.id.EditLoginSenha);
         botaologin = findViewById(R.id.BotaoLogin);

        }
}
