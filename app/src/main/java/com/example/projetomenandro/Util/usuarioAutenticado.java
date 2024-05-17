package com.example.projetomenandro.Util;

import android.content.res.Configuration;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class usuarioAutenticado {



    public static FirebaseUser usuarioLogado(){
        FirebaseAuth usuario = configuraBD.fireauth();
        return usuario.getCurrentUser();
    }

}
