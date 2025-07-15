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
public class ItemFactura implements Serializable {
    private Producto producto; // Producto asociado al ítem
    private int cantidad; // Cantidad comprada

    public ItemFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; } // Obtener producto
    public int getCantidad() { return cantidad; } // Obtener cantidad
    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad; // Calcular subtotal por ítem
    }
}
