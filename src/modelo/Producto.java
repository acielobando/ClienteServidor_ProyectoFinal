/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author camarona
 */
import java.io.Serializable;
public class Producto implements Serializable {
    private String codigoProducto; // Código del producto
    private String nombreProducto; // Nombre del producto
    private double precio; // Precio unitario
    private int cantidad; // Cantidad en inventario

    public Producto(String codigoProducto, String nombreProducto, double precio, int cantidad) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigoProducto() { return codigoProducto; } // Obtener código
    public String getNombreProducto() { return nombreProducto; } // Obtener nombre
    public double getPrecio() { return precio; } // Obtener precio
    public int getCantidad() { return cantidad; } // Obtener cantidad
}