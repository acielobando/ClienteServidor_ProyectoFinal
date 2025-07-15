package modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String usuario; // Nombre de usuario
    private String contrasena; // Contraseña

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario; // Asignar usuario
        this.contrasena = contrasena; // Asignar contraseña
    }

    public String getUsuario() {
        return usuario;
    } // Obtener usuario

    public String getContrasena() {
        return contrasena;
    } // Obtener contraseña

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    } // Establecer nueva contraseña
}