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
    public final int nr;
    private Color color;

    /**
     *
     */
    public ArrayList<Point2D> points;
    private int amount_points;
    private boolean if_i_am_closed;
   
    /**
     * Funkcja zracająca prostokąt wokół figury
     * @return UP
     */
    public Rectangle2D.Double getCover() {
        return new Rectangle2D.Double( getBounds2D().getX(),getBounds2D().getY(),getBounds2D().getWidth()+1,getBounds2D().getHeight()+1);
    }
    
    /**
     * Funkcja lącząca wierzchołki figury
     * @param x współ. odciąta punktu
     * @param y współ. rzędna punktu
     */
    public void LineTo (double x, double y) {
        this.lineTo(x, y);
        amount_points++;
        points.add(new Point2D.Double(x,y));
    }
    /**
     * Funkcja ustawiająca początek wielokąta
     * @param x współ. odcięta punktu
     * @param y współ, rzędna punktu
     */
    public void MoveTo (double x, double y) {
        this.moveTo(x, y);
        amount_points++;
        points.add(new Point2D.Double(x,y));
    }
    /**
     * Kontruktor klasy, potrzebny do zainicjowania tablicy punktów oraz koloru i numeru
     * @param nr numer naszej figury
     */
    public NewGeneralPath (int nr) {
        super();
        this.nr = nr;
        points = new ArrayList<>();
        color = Color.GRAY;
    }
    /**
     * Funkcja zwracająca i-ty wierzchołek figury
     * @param i 
     * @return 
     */
    public Point2D getPoint(int i) {
        if(i>points.size()) {
            return null;
        }
        return points.get(i);
    }
    
    /**
     *
     * @return
     */
    public Color getColor () {
        return color;
    }
    /**
     * Funkcja zwracająca ilośc punktów
     * @return 
     */
    public int getAmountPnts () {
        return amount_points;
    }
    /**
     * Funkcja ustawiająca kolor figury
     * @param color kolor, który ustawić 
     */
    public void setColor (Color color) {
        this.color = color;
    }
    /**
     * Funkcja sprawdzająca czy punkt należy do obszaru wielokąta
     * @param x współ. odcięta
     * @param y współ. rzędna
     * @return boolean czy punkt należy
     */
    public boolean isHit (float x, float y) {
        return getBounds2D().contains(x, y);
    }
    /**
     * Funkcja kończąca tworzenie wielokąta i
     * zmieniająca jego stan na "skończony"
     */
    public void closenPath() {
        super.closePath();
        if_i_am_closed = true;
    }
    /**
     * Funkcja sprawdzająca czy wielokąt jest skończony
     * @return 
     */
    public boolean isClosed () {
        return if_i_am_closed;
    }
}
