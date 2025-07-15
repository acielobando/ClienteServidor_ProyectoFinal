/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

public class Respuesta implements Serializable {
    private boolean exito; // Indica si fue exitosa
    private String mensaje; // Mensaje informativo
    private Object datos; // Datos retornados

    public Respuesta(boolean exito, String mensaje, Object datos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public boolean isExito() { return exito; } // Obtener estado de éxito
    public String getMensaje() { return mensaje; } // Obtener mensaje
    public Object getDatos() { return datos; } // Obtener datos
}
