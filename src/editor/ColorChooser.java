/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mateusz
 */
public class ColorChooser extends javax.swing.JDialog {

    /**
     * Zwykły kontrukor klasy JDialog
     * @param Owner właściciel Dialogu
     * @param modal wartość odpowiadająca czy Dialog jest "samodzielny"
     * @param rec prostokąt, którego chcemy pomalować
     * @param panel JPanel na którym pracujemy
     */
    public ColorChooser(JFrame Owner, boolean modal, NewRectangle rec, JPanel panel) {
        super(Owner,modal);
        initComponents(rec, panel);
    }
    /**
     * Zwykły kontrukor klasy JDialog
     * @param Owner właściciel Dialogu
     * @param modal wartość odpowiadająca czy Dialog jest "samodzielny"
     * @param eli elipsa, którą chcemy pomalować
     * @param panel JPanel na którym pracujemy
     */
    public ColorChooser(JFrame Owner, boolean modal, NewEllipse eli, JPanel panel) {
        super(Owner,modal);
        initComponents(eli, panel);
    }
    /**
     * Zwykły kontrukor klasy JDialog
     * @param Owner właściciel Dialogu
     * @param modal wartość odpowiadająca czy Dialog jest "samodzielny"
     * @param general wielokąt, którego chcemy pomalować
     * @param panel JPanel na którym pracujemy
     */
    public ColorChooser(JFrame Owner, boolean modal, NewGeneralPath general, JPanel panel) {
        super(Owner,modal);
        initComponents(general, panel);
    }
    /**
     * Inicjiwanie JDialogu
     * @param rec prostokąt, któremu zmieniamy kolor 
     * @param panel panel na którym pracujemy
     */
    @SuppressWarnings("unchecked")
    private void initComponents(NewRectangle rec, JPanel panel) {

        jColorChooser1 = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jColorChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 646, 344));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt, rec, panel);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 351, -1, -1));

        pack();
    }
    
    /**
     * Inicjiwanie JDialogu
     * @param eli elipsa, które zmieniamy kolor 
     * @param panel panel na którym pracujemy
     */
    private void initComponents(NewEllipse eli, JPanel panel) {

        jColorChooser1 = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jColorChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 646, 344));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt, eli, panel);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 351, -1, -1));

        pack();
    }
    
    /**
     * Inicjiwanie JDialogu
     * @param general wielokąt, któremu zmieniamy kolor 
     * @param panel panel na którym pracujemy
     */
    private void initComponents(NewGeneralPath general, JPanel panel) {

        jColorChooser1 = new javax.swing.JColorChooser();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jColorChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 646, 344));

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt, general, panel);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 351, -1, -1));

        pack();
    }
    /**
     * Funkcja obsługująca zdarzenie wyboru koloru
     * @param evt obsługa zdarzenia
     * @param rec prostokąt, któremu zmieniamy kolor
     * @param panel panel na którym pracujemy
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, NewRectangle rec, JPanel panel) {
        rec.setColor(jColorChooser1.getColor());
        panel.repaint();
        setVisible(false);
    }
    
    /**
     * Funkcja obsługująca zdarzenie wyboru koloru
     * @param evt obsługa zdarzenia
     * @param eli elipsa, której zmieniamy kolor
     * @param panel panel na którym pracujemy
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, NewEllipse eli, JPanel panel) {
        eli.setColor(jColorChooser1.getColor());
        panel.repaint();
        setVisible(false);
    }
    /**
     * Funkcja obsługująca zdarzenie wyboru koloru
     * @param evt obsługa zdarzenia
     * @param general wielokąt, któremu zmieniamy kolor
     * @param panel panel na którym pracujemy
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, NewGeneralPath general, JPanel panel) {
        general.setColor(jColorChooser1.getColor());
        panel.repaint();
        setVisible(false);
    }

    private Color color;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JColorChooser jColorChooser1;
    // End of variables declaration//GEN-END:variables
}
