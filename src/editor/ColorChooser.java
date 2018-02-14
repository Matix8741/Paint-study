/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mateusz
 */
public class ColorChooser extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstrukor klasy JDialog
	 * 
	 * @param Owner
	 *            wlasciciel Dialogu
	 * @param modal
	 *            wartosc odpowiadajaca czy Dialog jest "samodzielny"
	 * @param rec
	 *            prostokat, ktorego chcemy pomalowac
	 * @param panel
	 *            JPanel na ktorym pracujemy
	 */
	public ColorChooser(JFrame Owner, boolean modal, NewRectangle rec, JPanel panel) {
		super(Owner, modal);
		initComponents(rec, panel);
	}

	/**
	 * Konstrukor klasy JDialog
	 * 
	 * @param Owner
	 *            wlasciciel Dialogu
	 * @param modal
	 *            wartosc odpowiadajaca czy Dialog jest "samodzielny"
	 * @param eli
	 *            elipsa, ktora chcemy pomalowac
	 * @param panel
	 *            JPanel na ktorym pracujemy
	 */
	public ColorChooser(JFrame Owner, boolean modal, NewEllipse eli, JPanel panel) {
		super(Owner, modal);
		initComponents(eli, panel);
	}

	/**
	 * Konstrukor klasy JDialog
	 * 
	 * @param Owner
	 *            wlasciciel Dialogu
	 * @param modal
	 *            wartosc odpowiadajaca czy Dialog jest "samodzielny"
	 * @param general
	 *            wielokat, ktorego chcemy pomalowac
	 * @param panel
	 *            JPanel na ktorym pracujemy
	 */
	public ColorChooser(JFrame Owner, boolean modal, NewGeneralPath general, JPanel panel) {
		super(Owner, modal);
		initComponents(general, panel);
	}

	/**
	 * Inicjiwanie JDialogu
	 * 
	 * @param rec
	 *            prostokat, ktoremu zmieniamy kolor
	 * @param panel
	 *            panel na ktorym pracujemy
	 */
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
	 * 
	 * @param eli
	 *            elipsa, ktorej zmieniamy kolor
	 * @param panel
	 *            panel na ktorym pracujemy
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
	 * 
	 * @param general
	 *            wielokat, ktoremu zmieniamy kolor
	 * @param panel
	 *            panel na ktorym pracujemy
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
	 * Funkcja obslugujaca zdarzenie wyboru koloru
	 * 
	 * @param evt
	 *            obsluga zdarzenia
	 * @param rec
	 *            prostokat, ktoremu zmieniamy kolor
	 * @param panel
	 *            panel na ktorym pracujemy
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, NewRectangle rec, JPanel panel) {
		rec.setColor(jColorChooser1.getColor());
		panel.repaint();
		setVisible(false);
	}

	/**
	 * Funkcja obslugujaca zdarzenie wyboru koloru
	 * 
	 * @param evt
	 *            obsluga zdarzenia
	 * @param eli
	 *            elipsa, ktorej zmieniamy kolor
	 * @param panel
	 *            panel na ktorym pracujemy
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, NewEllipse eli, JPanel panel) {
		eli.setColor(jColorChooser1.getColor());
		panel.repaint();
		setVisible(false);
	}

	/**
	 * Funkcja obslugujaca zdarzenie wyboru koloru
	 * 
	 * @param evt
	 *            obsluga zdarzenia
	 * @param general
	 *            wielokat, ktoremu zmieniamy kolor
	 * @param panel
	 *            panel na ktorym pracujemy
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt, NewGeneralPath general, JPanel panel) {
		general.setColor(jColorChooser1.getColor());
		panel.repaint();
		setVisible(false);
	}

	private javax.swing.JButton jButton1;
	private javax.swing.JColorChooser jColorChooser1;
}
