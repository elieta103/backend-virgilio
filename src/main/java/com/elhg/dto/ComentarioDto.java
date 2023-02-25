package com.elhg.dto;

public class ComentarioDto {
    private Long id;
    private String nombre;
    private String contenido;

    public ComentarioDto(Long id , String nombre, String contenido) {
        this.id = id;
        this.nombre = nombre;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
