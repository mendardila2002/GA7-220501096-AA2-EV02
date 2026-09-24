package com.boutique.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Modelo de dominio que encapsula los datos de una orden de compra en Boutique Moderna.
 */
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigoPedido;
    private String nombreCliente;
    private String email;
    private String telefono;
    private String metodoEntrega; // "Envío a Domicilio" o "Recogida en Boutique"
    private String direccion;
    private String notas;
    private Producto producto;
    private String tallaSeleccionada;
    private int cantidad;
    private double total;
    private LocalDateTime fechaCreacion;

    public Pedido() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Pedido(String codigoPedido, String nombreCliente, String email, String telefono,
                  String metodoEntrega, String direccion, String notas, Producto producto,
                  String tallaSeleccionada, int cantidad, double total) {
        this.codigoPedido = codigoPedido;
        this.nombreCliente = nombreCliente;
        this.email = email;
        this.telefono = telefono;
        this.metodoEntrega = metodoEntrega;
        this.direccion = direccion;
        this.notas = notas;
        this.producto = producto;
        this.tallaSeleccionada = tallaSeleccionada;
        this.cantidad = cantidad;
        this.total = total;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters
    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMetodoEntrega() {
        return metodoEntrega;
    }

    public void setMetodoEntrega(String metodoEntrega) {
        this.metodoEntrega = metodoEntrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTallaSeleccionada() {
        return tallaSeleccionada;
    }

    public void setTallaSeleccionada(String tallaSeleccionada) {
        this.tallaSeleccionada = tallaSeleccionada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Retorna la fecha de creación formateada en formato legible en español.
     */
    public String getFechaFormateada() {
        if (fechaCreacion == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, hh:mm a", new Locale("es", "CO"));
        return fechaCreacion.format(formatter);
    }

    /**
     * Retorna el total liquidado formateado en Pesos Colombianos (COP).
     */
    public String getTotalFormateado() {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        formato.setMaximumFractionDigits(0);
        return formato.format(total) + " COP";
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo='" + codigoPedido + '\'' +
                ", cliente='" + nombreCliente + '\'' +
                ", total=" + total +
                ", producto=" + (producto != null ? producto.getNombre() : "null") +
                '}';
    }
}
