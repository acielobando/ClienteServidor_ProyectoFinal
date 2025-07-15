package server;

import helper.Helper;
import modelo.*;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import modelo.Respuesta;
import modelo.Solicitud;
public class ClientThread extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    // Mapa de usuarios: username → Usuario
    private static Map<String, Usuario> usuarios = new HashMap<>();

    static {
        // Agregamos usuario administrador por defecto
        usuarios.put("admin", new Usuario("admin", "1234"));
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
                manejarLogin((String[]) datos);
                break;
            case "guardar_cliente":
                enviarRespuesta(new Respuesta(true, "Cliente guardado exitosamente.", null));
                break;
            case "guardar_producto":
                enviarRespuesta(new Respuesta(true, "Producto registrado", null));
                break;
            case "crearFactura":
                Factura factura = (Factura) datos;
                System.out.println("Factura recibida: " + factura.getId());
                enviarRespuesta(new Respuesta(true, "Factura creada con total: " + factura.calcularTotal(), null));
                break;
            case "cambiar_contrasena":
                manejarCambioContrasena((String[]) datos);
                break;
            default:
                enviarRespuesta(new Respuesta(false, "Solicitud no reconocida", null));
        }
    }

    private void manejarLogin(String[] datos) throws IOException {
        String usuario = datos[0];
        String contrasena = datos[1];

        boolean valido = usuarios.containsKey(usuario) && usuarios.get(usuario).getContrasena().equals(contrasena);
        enviarRespuesta(new Respuesta(valido, valido ? "Bienvenido" : "Credenciales incorrectas", null));
    }

    private void manejarCambioContrasena(String[] datos) throws IOException {
        String usuario = datos[0];
        String actual = datos[1];
        String nueva = datos[2];

        if (!usuarios.containsKey(usuario)) {
            enviarRespuesta(new Respuesta(false, "Usuario no encontrado", null));
            return;
        }

        Usuario u = usuarios.get(usuario);
        if (!u.getContrasena().equals(actual)) {
            enviarRespuesta(new Respuesta(false, "Contraseña actual incorrecta", null));
            return;
        }

        u.setContrasena(nueva);
        enviarRespuesta(new Respuesta(true, "Contraseña actualizada correctamente", null));
    }

    private void enviarRespuesta(Respuesta respuesta) throws IOException {
        out.writeObject(respuesta);
        out.flush();
    }
    
    
private static final Map<String, Producto> inventario = new HashMap<>();
static {
   
    inventario.put("Laptop",  new Producto("Laptop1","Laptop", 800.0, 10));
    inventario.put("Mouse",   new Producto("Mouse1","Mouse",  20.0, 50));
    inventario.put("Teclado", new Producto("Teclado1","Teclado",30.0, 25));
}

private void procesarSolicitud2(Solicitud solicitud) throws IOException {
    String tipo = solicitud.getTipo();
    Object datos = solicitud.getContenido();

    switch (tipo) {
     

        case "verificar_stock": {
            Object[] arr = (Object[]) datos;
            String nombre = (String) arr[0];
            int cantidad = (int) arr[1];

            Producto p = inventario.get(nombre);
            if (p == null) {
                enviarRespuesta(new Respuesta(false, "Producto no encontrado.", null));
                return;
            }
            if (cantidad > p.getCantidad()) {
                enviarRespuesta(new Respuesta(false,
                        "Cantidad solicitada excede el stock. Disponible: " + p.getCantidad(), null));
                return;
            }

          
            enviarRespuesta(new Respuesta(true, "Stock disponible.", p));
            break;
        }

        // ...
    }
}

}
