/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import java.awt.geom.*;

/**
 *
 * @author Mateusz
 */
public class NewEllipse extends Ellipse2D.Double {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Color color;

	/**
	 *
	 */
	public final int nr;

	/**
	 * Funkcja zwracajaca kolor figury
	 * 
	 * @return Kolor figury
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Funkcja zracajaca prostokat woko³‚ figury
	 * 
	 * @return UP
	 */

	public Rectangle2D.Double getCover() {
		return new Rectangle2D.Double(getBounds2D().getX(), getBounds2D().getY(), getBounds2D().getWidth() + 1,
				getBounds2D().getHeight() + 1);
	}

	/**
	 * Funkcja ustawiajaca kolor figury
	 * 
	 * @param color
	 *            kolor, ktory chcemy ustawic
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param nr
	 */
	public NewEllipse(double x, double y, double width, double height, int nr) {

		this.nr = nr;
		setFrame(x, y, width, height);
		color = Color.GRAY;
	}

	/**
	 * Funkcja sprawdzajaca czy figura jest na tym punkcie
	 * 
	 * @param x
	 *            wspolrzedna odcieta
	 * @param y
	 *            wsporzedna rzedna
	 * @return true jesli sie znajduje, false jezeli nie
	 */
	public boolean isHit(double x, double y) {

		return getBounds2D().contains(x, y);
	}

	/**
	 * Funkcja zmieniajaca wspolrzedna odcieta
	 * 
	 * @param x
	 *            wartosc o jaka zmienic x
	 */
	public void addX(double x) {
		this.x += x;
	}

	/**
	 * Funkcja zmieniajaca wspolrzedna rzedna
	 * 
	 * @param y
	 *            wartosc o jaka zmienic y
	 */
	public void addY(double y) {
		this.y += y;
	}

	/**
	 * Funkcja zmieniajaca szerokosc figury
	 * 
	 * @param w
	 *            wartosc o jaka zmienic szerokosc
	 */
	public void addWidth(double w) {

		this.width += w;
	}

	/**
	 * Funkcja zmieniajaca wysokosc figury
	 * 
	 * @param h
	 *            wartosc o jaka zmienic wysokosc
	 */
	public void addHeight(double h) {

		this.height += h;
	}

}
