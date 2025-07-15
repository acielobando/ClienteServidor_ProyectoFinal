package client;

import helper.Helper;
import modelo.Producto;
import modelo.Respuesta;
import modelo.Solicitud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RegistrarProductoForm extends JFrame {
    private JPanel panelRegistrar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JButton btnRegistrar;
    private JButton btnVolver;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public RegistrarProductoForm(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
        this.socket = socket;
        this.out = out;
        this.in = in;

        setTitle("Registrar Producto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(panelRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigo = txtCodigo.getText();
                    String nombre = txtNombre.getText();
                    double precio = Double.parseDouble(txtPrecio.getText());
                    int cantidad = Integer.parseInt(txtCantidad.getText());

                    Producto producto = new Producto(codigo, nombre, precio, cantidad);
                    Solicitud solicitud = new Solicitud("registrarProducto", producto);
                    out.writeObject(solicitud);
                    out.flush();

                    Respuesta respuesta = (Respuesta) in.readObject();
                    Helper.mostrarMensaje(respuesta.getMensaje());

                } catch (Exception ex) {
                    Helper.mostrarMensaje("Error: " + ex.getMessage());
                }
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}