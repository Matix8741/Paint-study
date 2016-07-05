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
    
    private Color color;

    /**
     *
     */
    public final int nr;
    
    /**
     * Funkcja ustawiająca kolor figury
     * @param color kolor, który chcemy ustawić
     */
     public void setColor (Color color) {
        this.color = color;
    }
     
     /**
      * Funkcja zwracająca kolor figury
      * @return Kolor figury
      */
    public Color getColor () {
        return color;
    }
    /**
     * Funkcja zracająca prostokąt wokół figury
     * @return UP
     */
    public Rectangle2D.Double getCover() {
        return new Rectangle2D.Double( getBounds2D().getX()-1,getBounds2D().getY()-1,getBounds2D().getWidth()+1,getBounds2D().getHeight()+1);
    }
    /**
     * Konstruktor potrzebny do ustawienia koloru i nadania numerka naszej figurze
     * @param x współ. odcięta
     * @param y współ. rzędna
     * @param width szerokość
     * @param hight wysokość
     * @param nr numer naszej figury
     */
    public NewRectangle ( double x, double y, double width, double hight, int nr ) {
        color = Color.GRAY;
        this.nr = nr; 
        setFrame(x, y, width, hight);
    }
    /**
     * Funkcja sprawdzająca czy figury jest na tym punkcie 
     * @param x współ. odcięta
     * @param y współ. rzędna
     * @return true jeśli się znajduje, false jeżeli nie
     */
    public boolean isHit (double x, double y) {
        
        return getBounds2D().contains(x, y);
    }
    /**
     * Funkcja zmieniająca współ. odciętą
     * @param x wartość o jaką zmienić x
     */
    public void addX (double x) {
        this.x+=x;
    }
    /**
     * Funkcja zmieniająca współ. rzędną
     * @param y wartość o jaką zmienić y
     */
    public void addY (double y) {
        this.y+=y;
    }
    /**
     * Funkcja zmieniająca szerokość figury
     * @param w wartość o jaką zmienić szerokość
     */
    public void addWidth(double w) {
            
        this.width += w;
    }
    /**
     * Funkcja zmieniająca wysokość figury
     * @param h wartość o jaką zmienić wysokość
     */
    public void addHeight(double h) {
            
        this.height += h;
    }
}
