package com.example.sierra_camilo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Producto> listaPrincipalProductos;
    private RecyclerView rvListadoProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarDatos();

        rvListadoProductos = findViewById(R.id.rv_listado_productos);

        AdaptadorPersonalizado miAdaptador = new AdaptadorPersonalizado(listaPrincipalProductos);

        rvListadoProductos.setAdapter(miAdaptador);
        rvListadoProductos.setLayoutManager(new LinearLayoutManager(this));

    }

    public void cargarDatos(){
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
}