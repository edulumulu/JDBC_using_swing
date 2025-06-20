
package Interfaz_gráfica;

import GestionBBDD.Gestion_BBDD;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Empleado;

/**
 * Permite al usuario modificar la contraseña selecionando el nombre de un registro de la base de datos
 * @author edulumulu
 */
public class Cambiar_contrasena extends javax.swing.JDialog {

    Gestion_BBDD gesBBDD = new Gestion_BBDD();
    ArrayList<Empleado> lista_empleados = new ArrayList<>();
    Connection con = gesBBDD.Conectarse();
    int indice;
    int idEmpleadoBBDD;
    String contrasena_vieja ;

    /**
     * Creates new form Cambiar_contrasena
     */
    public Cambiar_contrasena(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        lb_contraantigu.setText("");
        lb_contraantigu.setEnabled(false);
        lb_titulo1.setEnabled(false);
        lb_titulo2.setEnabled(false);
        tf_contranueva.setVisible(false);

        lista_empleados = gesBBDD.cargar_listado_empleados(con);
            
        //Cargo el comboBox con el nombre y apellido de los empleados introduciendo un campo "seleciona un empleado"
        if (!lista_empleados.isEmpty()) {
            String[] listado = new String[lista_empleados.size()];

            cb_combo.addItem("Seleciona un empleado");

            for (int i = 0; i < lista_empleados.size(); i++) {
                cb_combo.addItem(lista_empleados.get(i).getName() + " " + lista_empleados.get(i).getSurname());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No existe ningún empleado en la BBDD", "Error", JOptionPane.WARNING_MESSAGE);
            dispose();

        }

        // Listener para la selección del empleado que hace interactuable la modificación del campo
        cb_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cb_combo.getSelectedItem() != null && !cb_combo.getSelectedItem().toString().equals("Seleciona un empleado")) {

                    indice = cb_combo.getSelectedIndex();
                    idEmpleadoBBDD = lista_empleados.get(indice - 1).getId();

                    contrasena_vieja =lista_empleados.get(indice - 1).getPassword();
                    lb_contraantigu.setText(contrasena_vieja);
                    lb_contraantigu.setEnabled(true);
                    lb_titulo1.setEnabled(true);
                    lb_titulo2.setEnabled(true);
                    tf_contranueva.setVisible(true);

                }else{
                    lb_contraantigu.setText("");
                    lb_contraantigu.setEnabled(false);
                    lb_titulo1.setEnabled(false);
                    lb_titulo2.setEnabled(false);
                    tf_contranueva.setVisible(false);
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cb_combo = new javax.swing.JComboBox<>();
        bt_eliminar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        lb_titulo1 = new javax.swing.JLabel();
        lb_contraantigu = new javax.swing.JLabel();
        lb_titulo2 = new javax.swing.JLabel();
        tf_contranueva = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel1.setText("Seleciona el empleado que deseas modificar");

        jLabel2.setText("Empleados:");

        bt_eliminar.setText("Modificar");
        bt_eliminar.setToolTipText("");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        lb_titulo1.setText("Contraseña antigua  -->");

        lb_contraantigu.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        lb_contraantigu.setText("jLabel4");

        lb_titulo2.setText("Escriba la nueva contraseña:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bt_eliminar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lb_titulo2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tf_contranueva))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(cb_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lb_titulo1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lb_contraantigu)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(bt_salir)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cb_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_titulo1)
                    .addComponent(lb_contraantigu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_titulo2)
                    .addComponent(tf_contranueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(bt_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(bt_salir)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed

        // si la contraseña no es la misma que la anterior inserta el nuevo campo en la base de datos
        if (tf_contranueva.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tienes que rellenar la nueva contraseña", "Faltan campos por rellenar", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (tf_contranueva.getText().trim().equalsIgnoreCase(contrasena_vieja)) {
            JOptionPane.showMessageDialog(this, "La nueva contraseña debe ser diferente de --> " + lista_empleados.get(indice - 1).getPassword(), "Faltan campos por rellenar", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            String nuevo_password = tf_contranueva.getText();

            if (gesBBDD.modificar_campo(con, nuevo_password, idEmpleadoBBDD, "password")) {
                JOptionPane.showMessageDialog(this, "Modificada  la contraseña de " 
                        + lista_empleados.get(indice - 1).getName() + " " + lista_empleados.get(indice - 1).getSurname()+"\n"
                        +"\tAntigua contraseña --> " + contrasena_vieja +"\n"
                        + "\tNueva contraseña --> " + nuevo_password+".",
                        "Empleado borrado", JOptionPane.INFORMATION_MESSAGE);
                gesBBDD.desconectarse(con);
                dispose();
            }
    
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed
/**
 * Se desconecta de la BBDD y sale de la ventana al menú principal
 * @param evt 
 */
    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed

        gesBBDD.desconectarse(con);
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cambiar_contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cambiar_contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cambiar_contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cambiar_contrasena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cambiar_contrasena dialog = new Cambiar_contrasena(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_salir;
    private javax.swing.JComboBox<String> cb_combo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lb_contraantigu;
    private javax.swing.JLabel lb_titulo1;
    private javax.swing.JLabel lb_titulo2;
    private javax.swing.JTextField tf_contranueva;
    // End of variables declaration//GEN-END:variables
}
