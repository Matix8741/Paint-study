/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Imoprt uzywanych bibliotek
 */
package editor;

import java.awt.*;

/**
 *
 * @author Mateusz Rzepecki
 */
public class Editor {

	/**
	 * Funkcja do uruchomienia projektu
	 * 
	 * @param args
	 *            argument komend programu
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Window window = new Window();
			window.setVisible(true);
		});
	}

}
