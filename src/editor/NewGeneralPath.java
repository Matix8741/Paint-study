/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Mateusz
 */
public class NewGeneralPath extends GeneralPath.Float {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	public final int nr;
	private Color color;

	/**
	 *
	 */
	public ArrayList<Point2D> points;
	private int amount_points;
	private boolean if_i_am_closed;

	/**
	 * Funkcja zracajaca prostokat wokol‚ figury
	 * 
	 * @return UP
	 */
	public Rectangle2D.Double getCover() {
		return new Rectangle2D.Double(getBounds2D().getX(), getBounds2D().getY(), getBounds2D().getWidth() + 1,
				getBounds2D().getHeight() + 1);
	}

	/**
	 * Funkcja zwracajaca wierzcholki figury
	 * 
	 * @param x
	 *            wspolrzedna odcieta punktu
	 * @param y
	 *            wspolrzedna rzedna punktu
	 */
	public void LineTo(double x, double y) {
		this.lineTo(x, y);
		amount_points++;
		points.add(new Point2D.Double(x, y));
	}

	/**
	 * Funkcja ustawiajaca poczatek wielokata
	 * 
	 * @param x
	 *            wspolrzedna odcieta punktu
	 * @param y
	 *            wspolrzedna rzedna punktu
	 */
	public void MoveTo(double x, double y) {
		this.moveTo(x, y);
		amount_points++;
		points.add(new Point2D.Double(x, y));
	}

	/**
	 * Kontruktor klasy, potrzebny do zainicjowania tablicy punktow oraz koloru
	 * i numeru
	 * 
	 * @param nr
	 *            numer naszej figury
	 */
	public NewGeneralPath(int nr) {
		super();
		this.nr = nr;
		points = new ArrayList<>();
		color = Color.GRAY;
	}

	/**
	 * Funkcja zwracajaca i-ty wierzcholek figury
	 * 
	 * @param i
	 * @return
	 */
	public Point2D getPoint(final int i) {
		if (i > points.size()) {
			return null;
		}
		return points.get(i);
	}

	/**
	 *
	 * @return
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Funkcja zwracajaca ilosc punktow
	 * 
	 * @return
	 */
	public int getAmountPnts() {
		return amount_points;
	}

	/**
	 * Funkcja ustawiajaca kolor figury
	 * 
	 * @param color
	 *            kolor, ktory ustawic
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Funkcja sprawdzajaca czy punkt nalezy do obszaru wielokata
	 * 
	 * @param x
	 *            wspolrzedna odcieta punktu
	 * @param y
	 *            wspolrzedna rzedna punktu
	 * @return boolean czy punkt nalezy
	 */
	public boolean isHit(float x, float y) {
		return getBounds2D().contains(x, y);
	}

	/**
	 * Funkcja konczaca tworzenie wielokata i zmieniajaca jego stan na
	 * "skonczony"
	 */
	public void closenPath() {
		super.closePath();
		if_i_am_closed = true;
	}

	/**
	 * Funkcja sprawdzajaca czy wielokat jest skonczony
	 * 
	 * @return
	 */
	public boolean isClosed() {
		return if_i_am_closed;
	}
}
