package com.example.sierra_camilo;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

import java.io.Serializable;

public class Producto implements Serializable {


    private String id;

    private String nombre;
    private Double precio;
    private String urlimagen;

    public Producto(){}

    public Producto(String nombre, Double precio, String urlimagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.urlimagen = urlimagen;
    }
    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }
    //@Exclude
    public String getNombre() {
        return nombre;
    }
    //@Exclude
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) { this.precio = precio; }
    @PropertyName("url_imagen")
    public String getUrlimagen() { return urlimagen; }
    @PropertyName("url_imagen")
    public void setUrlimagen(String urlimagen) { this.urlimagen = urlimagen; }
}
