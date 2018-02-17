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
public class GeneralList extends ArrayList<NewGeneralPath> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Funkcja do ustawienia objektu na pierwszym miejscu w liscie
	 * 
	 * @param general
	 *            objekt, ktory chcemy zmienic
	 */
	public void setFirst(NewGeneralPath general) {
		if (this.size() < 2) {
			return;
		}
		int i = this.indexOf(general);
		Collections.swap(this, 0, i);
		for (int j = 1; j < i; j++) {
			Collections.swap(this, i, j);
		}
	}
}