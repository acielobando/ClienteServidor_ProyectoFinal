package client;

import helper.Helper;
import helper.JSystem;
import modelo.Solicitud;
import modelo.Respuesta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginForm extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private JButton btnSalir;

    public LoginForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Iniciar Sesión");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String usuario = txtUsuario.getText();
                    String contrasena = new String(txtContrasena.getPassword());

                    Socket socket = new Socket("localhost", 12345);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    Solicitud solicitud = new Solicitud("login", new String[]{usuario, contrasena});
                    out.writeObject(solicitud);

                    Respuesta respuesta = (Respuesta) in.readObject();
                    Helper.mostrarMensaje(respuesta.getMensaje());

                    if (respuesta.isExito()) {
                        new MainForm(socket, out, in).setVisible(true);
                        dispose();
                    } else {
                        socket.close();
                    }
                } catch (Exception ex) {
                    Helper.mostrarMensaje("Error de conexión: " + ex.getMessage());
                }
            }
        });

        btnSalir.addActionListener(e -> {
            if (JSystem.confirmarSalida()) {
                System.exit(0);
            }
        });
    }
}