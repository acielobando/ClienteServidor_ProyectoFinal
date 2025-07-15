package modelo;

import java.io.Serializable;
public class Cliente extends Usuario implements Serializable {
    private String id;
    private String nombre;
    private String correo;

    public Cliente(String id, String nombre, String correo, String usuario, String contrasena) {
        super(usuario, contrasena);
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
}