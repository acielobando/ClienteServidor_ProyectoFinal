/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;

// Lista enlazada simple para manejar ítems 
public class ListaItemFactura implements Serializable {
    private Nodo primero;

    private static class Nodo implements Serializable {
        ItemFactura dato;
        Nodo siguiente;

        Nodo(ItemFactura dato) {
            this.dato = dato;
        }
    }

    public void agregar(ItemFactura item) {
        Nodo nuevo = new Nodo(item);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
    }

    public double calcularTotal() {
        double total = 0;
        Nodo actual = primero;
        while (actual != null) {
            total += actual.dato.calcularSubtotal();
            actual = actual.siguiente;
        }
        return total;
    }

    public String mostrarItems() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = primero;
        while (actual != null) {
            Producto p = actual.dato.getProducto();
            sb.append(p.getNombreProducto()).append(" x ")
              .append(actual.dato.getCantidad())
              .append(" = ¢").append(actual.dato.calcularSubtotal())
              .append("\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }
}