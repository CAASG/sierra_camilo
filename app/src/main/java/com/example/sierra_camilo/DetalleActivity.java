package com.example.sierra_camilo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity {

    private TextView tvTituloDetalle, tvPrecioDetalle;
    private ImageView ivImagenPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle(getString(R.string.txt_detalleProd));

        tvTituloDetalle = findViewById(R.id.tv_tituloDetalle);
        tvPrecioDetalle = findViewById(R.id.tv_precioDetalle);
        ivImagenPrincipal = findViewById(R.id.iv_imagenDetalle);

        Producto miProductoAtrapado = (Producto) getIntent().getSerializableExtra("producto");

        tvTituloDetalle.setText(miProductoAtrapado.getNombre());
        tvPrecioDetalle.setText(miProductoAtrapado.getPrecio().toString());
        Picasso.get()
                .load(miProductoAtrapado.getUrlimagen())
                .error(R.drawable.ic_launcher_background)
                .into(ivImagenPrincipal);
    }
}