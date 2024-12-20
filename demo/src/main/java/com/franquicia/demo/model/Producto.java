package com.franquicia.demo.model;

import org.springframework.data.annotation.Id;

public class Producto {
    @Id
    private String id;
    private String nombre;
    private int stock;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
