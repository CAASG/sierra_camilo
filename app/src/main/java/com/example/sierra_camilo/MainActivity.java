package com.example.sierra_camilo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos = new ArrayList<>();
    private RecyclerView rvListadoProductos;
    private AdaptadorPersonalizado miAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.txt_listadoDeProductos));


        rvListadoProductos = findViewById(R.id.rv_listado_productos);

        miAdaptador = new AdaptadorPersonalizado(listaPrincipalProductos);

        miAdaptador.setOnItemClickListener(new AdaptadorPersonalizado.OnItemClickListener() {
            @Override
            public void onItemClick(Producto miProducto, int posicion) {

                Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
                intent.putExtra("producto",miProducto);
                startActivity(intent);
            }

            @Override
            public void onItemBtEliminarClick(Producto miProducto, int posicion) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("productos").document(miProducto.getId()).delete();

                listaPrincipalProductos.remove(posicion);
                miAdaptador.setListadoInformacion(listaPrincipalProductos);
            }
        });

        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPrincipalProductos.clear();
        cargarDatos();

    }

    public void cargarDatos(){

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("productos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for (DocumentSnapshot document: task.getResult()){
                        Producto productoAtrapado = document.toObject(Producto.class);
                        productoAtrapado.setId(document.getId());
                        listaPrincipalProductos.add(productoAtrapado);
                    }

                    miAdaptador.setListadoInformacion(listaPrincipalProductos);


                }else{
                    Toast.makeText(MainActivity.this, "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Producto producto1 = new Producto();
        producto1.setNombre("Computador HP");
        producto1.setPrecio(8000000.0);
        producto1.setUrlimagen("https://www.pngarts.com/files/4/HP-Laptop-PNG-Image.png");

        Producto producto2 = new Producto("Teclado DELL", 250000.0, "https://e7.pngegg.com/pngimages/710/272/png-clipart-computer-keyboard-computer-mouse-laptop-dell-keyboard-electronics-computer-keyboard.png");
        Producto producto3 = new Producto("Mouse", 130000.0, "https://e7.pngegg.com/pngimages/265/131/png-clipart-computer-mouse-logitech-gaming-mouse-g-pro-pelihiiri-scroll-wheel-computer-mouse-electronics-mouse.png");
        //inicializar el arraylist
        listaPrincipalProductos = new ArrayList<>();
        //agregar los productos al arraylist
        listaPrincipalProductos.add(producto1);
        listaPrincipalProductos.add(producto2);
        listaPrincipalProductos.add(producto3);
    }

    public void clickCerrarSession(View view){
        SharedPreferences miPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = miPreferencias.edit();
        myEditor.clear();
        myEditor.apply();

        startActivity(new Intent(this,PaginaWeb.class));
        finish();
    }

    public void clickAgregarProducto(View view){
        startActivity(new Intent(this, FormularioActivity.class));
    }
}