/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

public class Solicitud implements Serializable {
    private String tipo; // Tipo de operación
    private Object contenido; // Objeto enviado como contenido de la solicitud

    public Solicitud(String tipo, Object contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public String getTipo() { return tipo; } // Obtener tipo
    public Object getContenido() { return contenido; } // Obtener contenido
}
