package server;
import modelo.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientThread extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private static Map<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put("admin", "1234");
    }

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Solicitud solicitud;
            while ((solicitud = (Solicitud) in.readObject()) != null) {
                procesarSolicitud(solicitud);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cliente desconectado.");
        }
    }

    private void procesarSolicitud(Solicitud solicitud) throws IOException {
        String tipo = solicitud.getTipo();
        Object datos = solicitud.getContenido();

        switch (tipo) {
            case "login":
                String[] credenciales = (String[]) datos;
                String usuario = credenciales[0];
                String contrasena = credenciales[1];
                boolean valido = usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contrasena);
                enviarRespuesta(new Respuesta(valido, valido ? "Bienvenido" : "Credenciales incorrectas", null));
                break;
            case "registrarProducto":
                enviarRespuesta(new Respuesta(true, "Producto registrado", null));
                break;
            case "crearFactura":
                Factura factura = (Factura) datos;
                System.out.println("Factura recibida: " + factura.getId());
                enviarRespuesta(new Respuesta(true, "Factura creada con total: " + factura.calcularTotal(), null));
                break;
            default:
                enviarRespuesta(new Respuesta(false, "Solicitud no reconocida", null));
        }
    }

    private void enviarRespuesta(Respuesta respuesta) throws IOException {
        out.writeObject(respuesta);
        out.flush();
    }
}