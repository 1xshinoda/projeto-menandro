package com.example.projetomenandro.Util;


import com.google.firebase.auth.FirebaseAuth;

public class configuraBD {
    private static FirebaseAuth auth;

        public static FirebaseAuth fireauth(){
            if(auth == null){
                auth =FirebaseAuth.getInstance();

            }
            return auth;


        }
}
