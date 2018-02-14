/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Mateusz
 */
public class Ellipse_List extends ArrayList<NewEllipse> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Wstawianie objektu na pirwszym miejscu na liscie
	 * 
	 * @param i
	 *            z ktorego miejsca zaczynamy
	 */
	public void setFirst(int i) {
		if (i < 1) {
			return;
		}
		Collections.swap(this, 0, i);
		for (int j = 1; j < i; j++) {

			Collections.swap(this, i, j);
		}
	}

	/**
	 * Funkcja do ustawienia objektu na pierwszym miejscu w liscie
	 * 
	 * @param eli
	 *            objekt, ktory chcemy zmienic
	 */
	public void setFirst(NewEllipse eli) {
		if (this.size() < 2) {
			return;
		}
		int i = this.indexOf(eli);
		Collections.swap(this, 0, i);
		for (int j = 1; j < i; j++) {

			Collections.swap(this, i, j);
		}
	}
}
