package helper;

import modelo.ItemFactura;
import modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class FacturaSession {
    private static final FacturaSession INSTANCE = new FacturaSession();

    private String cliente;                 // opcional
    private final List<ItemFactura> items = new ArrayList<>();

    private FacturaSession() {}

    public static FacturaSession get() { return INSTANCE; }

    public void setCliente(String c) { this.cliente = c; }
    public String getCliente() { return cliente; }

    public void agregarItem(Producto p, int cantidad) {
        items.add(new ItemFactura(p, cantidad));
    }

    public List<ItemFactura> getItems() { return items; }

    public double getTotal() {
        double t = 0.0;
        for (ItemFactura it : items) {
            t += it.getProducto().getPrecio() * it.getCantidad();
        }
        return t;
    }

    public void limpiar() {
        cliente = null;
        items.clear();
    }
}