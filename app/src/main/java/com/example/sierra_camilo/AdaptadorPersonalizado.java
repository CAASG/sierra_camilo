package com.example.sierra_camilo;

import android.animation.PropertyValuesHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends RecyclerView.Adapter<AdaptadorPersonalizado.ViewHolder> {

    private ArrayList<Producto> listadoInformacion;

    public AdaptadorPersonalizado(ArrayList<Producto> listadoInformacion) {
        this.listadoInformacion = listadoInformacion;
    }

    @NonNull
    @Override
    //e=se engancha el diseno del item
    public AdaptadorPersonalizado.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_productos,parent,false);
        return new ViewHolder(miview);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPersonalizado.ViewHolder holder, int position) {
        Producto miProducto = listadoInformacion.get(position);
        holder.enlazar(miProducto);

    }

    @Override
    public int getItemCount() {
        return listadoInformacion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre, tvPrecio;
        private ImageView ivProductoo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tv_itemNombre);
            tvPrecio = itemView.findViewById(R.id.tv_itemPrecio);
            ivProductoo = itemView.findViewById(R.id.iv_itemImagen);
        }

        //se enlazan con el public de arriba
        public void enlazar(Producto miProdcuto){
            tvNombre.setText(miProdcuto.getNombre());
            tvPrecio.setText(miProdcuto.getPrecio().toString());
        }
    }
}
