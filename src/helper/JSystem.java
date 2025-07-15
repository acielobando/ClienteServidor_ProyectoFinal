package helper;
import javax.swing.*;

public class JSystem {
    public static boolean confirmarSalida() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea salir del sistema?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }
}