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
	 * @param frameOwner
	 *            wlasciciel Dialogu
	 * @param isModal
	 *            wartosc odpowiadajaca czy Dialog jest "samodzielny"
	 * @param recangle
	 *            prostokat, ktorego chcemy pomalowac
	 * @param panel
	 *            JPanel na ktorym pracujemy
	 */
	public ColorChooser(JFrame frameOwner, boolean isModal, NewRectangle recangle, JPanel panel) {
		super(frameOwner, isModal);
		initComponents(recangle, panel);
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

		jColorChooser = new javax.swing.JColorChooser();
		jButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		getContentPane().add(jColorChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 646, 344));

		jButton.setText("OK");
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt, rec, panel);
			}
		});
		getContentPane().add(jButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 351, -1, -1));

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

		jColorChooser = new javax.swing.JColorChooser();
		jButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		getContentPane().add(jColorChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 646, 344));

		jButton.setText("OK");
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt, eli, panel);
			}
		});
		getContentPane().add(jButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 351, -1, -1));

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

		jColorChooser = new javax.swing.JColorChooser();
		jButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		getContentPane().add(jColorChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 646, 344));

		jButton.setText("OK");
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt, general, panel);
			}
		});
		getContentPane().add(jButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 351, -1, -1));

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
		rec.setColor(jColorChooser.getColor());
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
		eli.setColor(jColorChooser.getColor());
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
		general.setColor(jColorChooser.getColor());
		panel.repaint();
		setVisible(false);
	}

	private javax.swing.JButton jButton;
	private javax.swing.JColorChooser jColorChooser;
}
