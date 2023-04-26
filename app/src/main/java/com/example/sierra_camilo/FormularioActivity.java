package com.example.sierra_camilo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = findViewById(R.id.et_nombreNuevoProducto);
        etPrecio = findViewById(R.id.et_precioNuevoProducto);
        etImagen = findViewById(R.id.et_imagenNuevoProducto);
    }

    public void clickAgregar(View view){
        String nombre = etNombre.getText().toString();
        Double precio = Double.parseDouble(etPrecio.getText().toString());
        String url = etImagen.getText().toString();

        Producto nuevoProducto = new Producto();
        nuevoProducto.setPrecio(precio);
        nuevoProducto.setUrlimagen(url);
        nuevoProducto.setNombre(nombre);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").add(nuevoProducto);
        
        Toast.makeText(this, "SE CREO EL PRODUCTO", Toast.LENGTH_SHORT).show();
        finish();
    }
}