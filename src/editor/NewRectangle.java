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
public class NewRectangle extends Rectangle2D.Double {

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
	 * Funkcja ustawiajaca kolor figury
	 * 
	 * @param color
	 *            kolor, ktory chcemy ustawic
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Funkcja zwracajaca kolor figury
	 * 
	 * @return Kolor figury
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Funkcja zracajaca prostokat wokol‚ figury
	 * 
	 * @return UP
	 */
	public Rectangle2D.Double getCover() {
		return new Rectangle2D.Double(getBounds2D().getX() - 1, getBounds2D().getY() - 1, getBounds2D().getWidth() + 1,
				getBounds2D().getHeight() + 1);
	}

	/**
	 * Konstruktor potrzebny do ustawienia koloru i nadania numerka naszej
	 * figurze
	 * 
	 * @param x
	 *            wspolrzedna odcieta punktu
	 * @param y
	 *            wspolrzedna rzedna punktu
	 * @param width
	 *            szerokosc
	 * @param hight
	 *            wysokosc
	 * @param nr
	 *            numer naszej figury
	 */
	public NewRectangle(double x, double y, double width, double hight, int nr) {
		color = Color.GRAY;
		this.nr = nr;
		setFrame(x, y, width, hight);
	}

	/**
	 * Funkcja sprawdzajaca czy figury jest na tym punkcie
	 * 
	 * @param x
	 *            wspolrzedna odcieta punktu
	 * @param y
	 *            wspolrzedna rzedna punktu
	 * @return true jezeli sie znajduje, false jezeli nie
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
