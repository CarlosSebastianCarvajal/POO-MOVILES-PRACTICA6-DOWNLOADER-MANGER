package com.example.downloader_manager.models;

public class Cuento {

    String Id, Autor, Categorias, Nombre, Nombre_Archivo, Url;

    public Cuento(String id, String autor, String categorias, String nombre, String nombre_Archivo, String url) {
        Id = id;
        Autor = autor;
        Categorias = categorias;
        Nombre = nombre;
        Nombre_Archivo = nombre_Archivo;
        Url = url;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getCategorias() {
        return Categorias;
    }

    public void setCategorias(String categorias) {
        Categorias = categorias;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String nombre_Archivo) {
        Nombre_Archivo = nombre_Archivo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
