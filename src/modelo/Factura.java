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
import java.util.ArrayList;
import java.util.List;

public class Factura implements Serializable {
    private String id; // Código o ID de la factura
    private Cliente cliente; // Cliente al que pertenece la factura
    private List<ItemFactura> items; // Lista de ítems en la factura

    public Factura(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.items = new ArrayList<>();
    }

    public void agregarItem(ItemFactura item) {
        items.add(item); // Agregar ítem a la factura
    }

    public double calcularTotal() {
        return items.stream().mapToDouble(ItemFactura::calcularSubtotal).sum(); // Sumar subtotales
    }

    public String getId() { return id; } // Obtener ID
    public Cliente getCliente() { return cliente; } // Obtener cliente
    public List<ItemFactura> getItems() { return items; } // Obtener lista de ítems
}