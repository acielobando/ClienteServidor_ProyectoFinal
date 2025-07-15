/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client;


import helper.FacturaSession;
import modelo.ItemFactura;
import modelo.Producto;

import javax.swing.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class ExportarFactura extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ExportarFactura.class.getName());

   
private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;

    public ExportarFactura(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
        this.socket = socket;
        this.out = out;
        this.in = in;

        initComponents(); // generado por NetBeans
        configurarBotones(); // lógica personalizada
    }

    private void configurarBotones() {
        btnAceptar.addActionListener(e -> exportarFactura());
    }

    private void exportarFactura() {
        String formato = (String) comboFormato.getSelectedItem();
        if ("PDF".equalsIgnoreCase(formato)) {
            exportarComoPDF();
        } else {
            exportarComoTXT();
        }

        JOptionPane.showMessageDialog(this,
                "¡Factura exportada con éxito en formato " + formato + "!",
                "Exportación",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private void exportarComoTXT() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("factura.txt"))) {
            writer.println("Factura");
            writer.println("====================================");

            List<ItemFactura> items = FacturaSession.get().getItems();
            double total = 0;

            for (int i = 0; i < items.size(); i++) {
                ItemFactura item = items.get(i);
                Producto p = item.getProducto();
                int cantidad = item.getCantidad();
                double subtotal = p.getPrecio() * cantidad;
                total += subtotal;

                writer.println("N: " + (i + 1));
                writer.println("Código: " + p.getCodigoProducto());
                writer.println("Producto: " + p.getNombreProducto());
                writer.println("P.U: ₡" + String.format("%.2f", p.getPrecio()));
                writer.println("Cantidad: " + cantidad);
                writer.println("Subtotal: ₡" + String.format("%.2f", subtotal));
                writer.println("------------------------------------");
            }

            writer.println("TOTAL GENERAL: ₡" + String.format("%.2f", total));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al exportar TXT: " + ex.getMessage());
        }
    }

    private void exportarComoPDF() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new java.io.FileOutputStream("factura.pdf"));
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
            document.add(new Paragraph("Factura", font));
            document.add(new Paragraph("====================================", font));

            List<ItemFactura> items = FacturaSession.get().getItems();
            double total = 0;

            for (int i = 0; i < items.size(); i++) {
                ItemFactura item = items.get(i);
                Producto p = item.getProducto();
                int cantidad = item.getCantidad();
                double subtotal = p.getPrecio() * cantidad;
                total += subtotal;

                document.add(new Paragraph("N: " + (i + 1), font));
                document.add(new Paragraph("Código: " + p.getCodigoProducto(), font));
                document.add(new Paragraph("Producto: " + p.getNombreProducto(), font));
                document.add(new Paragraph("P.U: ₡" + String.format("%.2f", p.getPrecio()), font));
                document.add(new Paragraph("Cantidad: " + cantidad, font));
                document.add(new Paragraph("Subtotal: ₡" + String.format("%.2f", subtotal), font));
                document.add(new Paragraph("------------------------------------", font));
            }

            document.add(new Paragraph("TOTAL GENERAL: ₡" + String.format("%.2f", total), font));
            document.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al exportar PDF: " + ex.getMessage());
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboFormato = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Exportar Factura");

        comboFormato.setBackground(new java.awt.Color(169, 205, 229));
        comboFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormatoActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(169, 205, 229));
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFormatoActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JComboBox<String> comboFormato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
