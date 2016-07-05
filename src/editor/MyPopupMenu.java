/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Mateusz
 */
public class MyPopupMenu extends JPopupMenu {
    private JMenuItem usun;
    private JMenuItem anItem;
    /**
     * Kontruktor klasy 
     * @param frame JFrame na którym pracujemy
     * @param rec   prostokąt dla którego tworzymy PopupMenu
     * @param panel panel na którym pracujemy
     * @param Queue Kolejka, potrzebna do usuwania objektów
     */
    public MyPopupMenu(JFrame frame, NewRectangle rec, JPanel panel, Map Queue){
        anItem = new JMenuItem("Kolor zmien");
        usun = new JMenuItem("Usun");
        anItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                ColorChooser cc = new ColorChooser(frame, true, rec, panel);
                cc.setVisible(true);
            }
        });
        usun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event){
               // Queue.remove(rec.nr);
            }
        });
        add(anItem);
        add(usun);
    }
    /**
     * Kontruktor klasy 
     * @param frame JFrame na którym pracujemy
     * @param eli   elipsa dla której tworzymy PopupMenu
     * @param panel panel na którym pracujemy
     * @param Queue Kolejka, potrzebna do usuwania objektów
     */
    public MyPopupMenu(JFrame frame, NewEllipse eli, JPanel panel, Map Queue){
        anItem = new JMenuItem("Kolor zmien");
        usun = new JMenuItem("Usun");
        anItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                ColorChooser cc = new ColorChooser(frame, true, eli, panel);
                cc.setVisible(true);
            }
        });
        usun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event){
               // Queue.remove(eli.nr);
            }
        });
        add(anItem);
        add(usun);
    }
    
    /**
     * Kontruktor klasy 
     * @param frame JFrame na którym pracujemy
     * @param general wielokąt dla którego tworzymy PopupMenu
     * @param panel panel na którym pracujemy
     * @param Queue Kolejka, potrzebna do usuwania objektów
     */
    public MyPopupMenu(JFrame frame, NewGeneralPath general, JPanel panel,Map Queue){
        anItem = new JMenuItem("Kolor zmien");
        usun = new JMenuItem("Usun");
        anItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                ColorChooser cc = new ColorChooser(frame, true, general, panel);
                cc.setVisible(true);
            }
        });
        usun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event){
             //   Queue.remove(general.nr);
            }
        });
        add(anItem);
        add(usun);
    }
    
}
