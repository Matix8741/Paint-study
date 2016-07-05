
package editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Window extends JFrame {
    private JMenuItem info;
    private JMenu menu;
    private JMenuBar menubar;
    private JMenuItem menuitem1;
    private JMenuItem menuitem2;
    private JMenuItem menuitem3;
    /**
     * Najzwyklejszy kontruktor
     */
    public Window () {
        initUI();
    }
    /**
     * Funkcja inicjująca okienko
     */
    private void initUI() {
        Easel field = new Easel(this);
        add( field );
        field.setBackground(Color.white);
        menu= new JMenu("Rysuj");
        menubar = new JMenuBar();
        menuitem1 = new JMenuItem("Prostokat");
        menuitem1.addActionListener((java.awt.event.ActionEvent event) -> {
            field.ifdrawrectangle=true;
        });
        menuitem2 = new JMenuItem("Okrag");
        menuitem2.addActionListener((java.awt.event.ActionEvent event) -> {
            field.ifdrawellipse=true;
        });
        menuitem3 = new JMenuItem("WIelokat");
        menuitem3.addActionListener((java.awt.event.ActionEvent event) -> {
            field.ifdrawpolyline=true;
            field.if_first_click=true;
        });
        info = new JMenuItem("Info");
        info.addActionListener((java.awt.event.ActionEvent event) ->  {
            JDialog info_dialog = new JDialog(this,"Info",false);
            Label Info_text1 = new Label();
            Label Info_text2 = new Label();
            Label Info_text3 = new Label();
            Label Info_text4 = new Label();
            Label Info_text5 = new Label();
            Label Info_text6 = new Label();
            Label Info_text7 = new Label();
            info_dialog.add(Info_text1);
            info_dialog.add(Info_text2);
            info_dialog.add(Info_text3);
            info_dialog.add(Info_text4);
            info_dialog.add(Info_text5);
            info_dialog.add(Info_text6);
            info_dialog.add(Info_text7);
            info_dialog.setDefaultCloseOperation(1);
            Info_text1.setText("Program \"Editor\" jest wykonany przez Mateusz Rzepeckiego");
            Info_text2.setText("Służy do zaliczenia listy nr 6.");
            Info_text3.setText("Pozwala na:");
            Info_text4.setText("-rysownie dowolnego wielokąta oraz" + " elipsy");
            Info_text5.setText("-przesuwanie ich");
            Info_text6.setText("-skalowanie scrollem");
            Info_text7.setText("-zmiane koloru i usuwanie");
            info_dialog.setLayout( new GridLayout(7,1));
            info_dialog.pack();
            info_dialog.setVisible(true);
        });
        menu.add(menuitem1);
        menu.add(menuitem2);
        menu.add(menuitem3);
        menubar.add(menu);
        menubar.add(info);
        setJMenuBar(menubar);
        setTitle("Panel edycji");
        setSize(800,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}