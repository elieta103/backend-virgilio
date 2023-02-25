package com.elhg.exception;

import java.util.Date;

public class ErrorMessage {
  private int statusCodigo;
  private Date timestamp;
  private String mensaje;
  private String descripcion;

  public ErrorMessage(int statusCodigo, Date timestamp, String message, String description) {
    this.statusCodigo = statusCodigo;
    this.timestamp = timestamp;
    this.mensaje = mensaje;
    this.descripcion = description;
  }

  public int getStatusCodigo() {
    return statusCodigo;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMensaje() {
    return mensaje;
  }

  public String getDescripcion() {
    return descripcion;
  }
}