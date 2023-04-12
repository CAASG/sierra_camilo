package com.example.sierra_camilo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class PaginaWeb extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private SharedPreferences misPreferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_web);

        referenciar();

        misPreferencias = getSharedPreferences("tienda_app",MODE_PRIVATE);

        //VERIFICAR SI ESTA LOGUEADO
        if (misPreferencias.getBoolean("logueado",false)==true){
            Intent miIntent = new Intent(this,MainActivity.class);
            startActivity(miIntent);
            finish();
        }

    }

    private void referenciar() {
        etPassword = findViewById(R.id.et_contraseña);
        etUsuario = findViewById(R.id.et_correo);
    }

    public void clickIniciarSesion(View view) {
        String PASS = "123456";
        String USER = "Camilo";

        String passUser = etPassword.getText().toString();
        String userUser = etUsuario.getText().toString();

        if(PASS.equals(passUser)&&USER.equals(userUser)){

            SharedPreferences.Editor myEditor = misPreferencias.edit();
            myEditor.putBoolean("logueado", true);
            myEditor.apply();

            Intent miIntent = new Intent(this, MainActivity.class);
            startActivity(miIntent);
            finish();
        }else {
            Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show();
        }
    }
}