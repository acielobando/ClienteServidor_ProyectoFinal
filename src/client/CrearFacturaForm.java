package client;

import helper.Helper;
import modelo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CrearFacturaForm extends JFrame {
    private JPanel panelFactura;
    private JTextField txtIdFactura;
    private JTextField txtIdCliente;
    private JTextField txtNombreCliente;
    private JTextField txtCorreoCliente;
    private JTextField txtCodProducto;
    private JTextField txtNombreProducto;
    private JTextField txtPrecioProducto;
    private JTextField txtCantidadProducto;
    private JButton btnAgregarItem;
    private JButton btnCrearFactura;
    private JTextArea areaFactura;
    private JButton btnCerrar;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Factura factura;

    public CrearFacturaForm(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
        this.socket = socket;
        this.out = out;
        this.in = in;

        setTitle("Crear Factura");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panelFactura);

        btnAgregarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (factura == null) {
                    String idFactura = txtIdFactura.getText();
                    String idCliente = txtIdCliente.getText();
                    String nombre = txtNombreCliente.getText();
                    String correo = txtCorreoCliente.getText();
                    factura = new Factura(idFactura, new Cliente(idCliente, nombre, correo, "", ""));
                }

                String cod = txtCodProducto.getText();
                String nombre = txtNombreProducto.getText();
                double precio = Double.parseDouble(txtPrecioProducto.getText());
                int cantidad = Integer.parseInt(txtCantidadProducto.getText());

                Producto producto = new Producto(cod, nombre, precio, cantidad);
                ItemFactura item = new ItemFactura(producto, cantidad);
                factura.agregarItem(item);

                areaFactura.setText(factura.getItems().mostrarItems());
            }
        });

        btnCrearFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Solicitud solicitud = new Solicitud("crearFactura", factura);
                    out.writeObject(solicitud);
                    out.flush();

                    Respuesta respuesta = (Respuesta) in.readObject();
                    Helper.mostrarMensaje(respuesta.getMensaje());
                    factura = null;
                    areaFactura.setText("");
                } catch (Exception ex) {
                    Helper.mostrarMensaje("Error: " + ex.getMessage());
                }
            }
        });

        btnCerrar.addActionListener(e -> dispose());
    }
}
