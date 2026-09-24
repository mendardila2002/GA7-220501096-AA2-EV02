package com.boutique.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Modelo de dominio que representa una prenda exclusiva en Boutique Moderna.
 * Implementa Serializable según los estándares de especificación Java EE / Jakarta EE.
 */
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String categoria; // "HOMBRE" o "MUJER"
    private String descripcion;
    private double precio;
    private String imagenUrl;
    private List<String> tallas;
    private boolean enStock;
    private String composicion;

    // Constructor por defecto
    public Producto() {
        this.tallas = new ArrayList<>();
        this.enStock = true;
    }

    // Constructor completo
    public Producto(int id, String nombre, String categoria, String descripcion, double precio,
                    String imagenUrl, List<String> tallas, boolean enStock, String composicion) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.tallas = tallas != null ? tallas : new ArrayList<>();
        this.enStock = enStock;
        this.composicion = composicion;
    }

    // Constructor de conveniencia con tallas como varargs
    public Producto(int id, String nombre, String categoria, String descripcion, double precio,
                    String imagenUrl, String composicion, String... tallas) {
        this(id, nombre, categoria, descripcion, precio, imagenUrl, Arrays.asList(tallas), true, composicion);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public List<String> getTallas() {
        return tallas;
    }

    public void setTallas(List<String> tallas) {
        this.tallas = tallas;
    }

    public boolean isEnStock() {
        return enStock;
    }

    public void setEnStock(boolean enStock) {
        this.enStock = enStock;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    /**
     * Retorna las tallas formateadas como cadena de texto.
     */
    public String getTallasTexto() {
        if (tallas == null || tallas.isEmpty()) return "";
        return String.join(" • ", tallas);
    }

    /**
     * Retorna el precio formateado en Pesos Colombianos (COP).
     * Ejemplo: $ 480.000 COP
     */
    public String getPrecioFormateado() {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        formato.setMaximumFractionDigits(0);
        return formato.format(precio) + " COP";
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", enStock=" + enStock +
                '}';
    }
}
