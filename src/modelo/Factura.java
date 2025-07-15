/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.io.Serializable;

public class Factura implements Serializable {
    private String id; // Código o ID de la factura
    private Cliente cliente; // Cliente al que pertenece la factura
    private ListaItemFactura items; // Lista  de ítems

    public Factura(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.items = new ListaItemFactura();
    }

    public void agregarItem(ItemFactura item) {
        items.agregar(item); // Agregar ítem a la lista
    }

    public double calcularTotal() {
        return items.calcularTotal(); // Calcular total
    }

    public String getId() { return id; } // Obtener ID
    public Cliente getCliente() { return cliente; } // Obtener cliente
    public ListaItemFactura getItems() { return items; } // Obtener lista de ítems
}