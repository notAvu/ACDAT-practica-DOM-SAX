package Clases;

public class Producto {
    private int id;
    private String descripcion;
    private float precio;
    private int stock;

    public Producto(int id, String descripcion, float precio, int stock) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}
