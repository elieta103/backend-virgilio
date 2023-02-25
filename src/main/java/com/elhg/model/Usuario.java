package com.elhg.model;

import jakarta.persistence.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_generator")
  private long id;

  @Column(name = "nombre")
  private String nombre;

  @Column(name = "email")
  private String email;

  public Usuario() {
  }

  public Usuario(String nombre, String email) {
    this.nombre = nombre;
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Usuario{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
