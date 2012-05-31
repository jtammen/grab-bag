import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Aufgabe1 extends Frame implements MouseListener,
        MouseMotionListener, ActionListener, ItemListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private Vector figuren;
    private Point punkt = null;
    private String zeichenmodus;
    private boolean keycode = false;

    public Aufgabe1() {
        super();

        this.figuren = new Vector();
        this.zeichenmodus = "k";
        this.setSize(800, 600);
        this.setVisible(true);

        this.setTitle("Aufgabe 1");
        this.setBackground(Color.WHITE);

        // Zeichnen-Menue
        Menu ZeichnenMenu = new Menu("Zeichnen");
        MenuItem aMenuItem;
        aMenuItem = new MenuItem("Kreis");
        aMenuItem.addActionListener(this);
        aMenuItem.setActionCommand("k");
        ZeichnenMenu.add(aMenuItem);
        aMenuItem = new MenuItem("Rechteck");
        aMenuItem.addActionListener(this);
        aMenuItem.setActionCommand("r");
        ZeichnenMenu.add(aMenuItem);

        // Anzeige-Menue
        Menu AnzeigeMenu = new Menu("Anzeige");
        CheckboxMenuItem aCheckboxMenuItem = new CheckboxMenuItem("sichtbar");
        aCheckboxMenuItem.setState(true);
        aCheckboxMenuItem.addItemListener(this);
        aCheckboxMenuItem.setActionCommand("v");
        AnzeigeMenu.add(aCheckboxMenuItem);
        aMenuItem = new MenuItem("löschen");
        aMenuItem.addActionListener(this);
        aMenuItem.setActionCommand("l");
        AnzeigeMenu.add(aMenuItem);

        // Menue-Bar
        MenuBar bar = new MenuBar();
        bar.add(ZeichnenMenu);
        bar.add(AnzeigeMenu);
        setMenuBar(bar);

        // Als Listener für Mouse und Tastatur registrieren
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);

        // Window-Listener registrieren
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.setXORMode(this.getBackground());
        g.setFont(new Font("Arial", Font.PLAIN, 10));

        // Alle Figuren zeichnen
        Iterator it = this.figuren.iterator();
        while (it.hasNext()) {
            Figur f = (Figur) it.next();
            f.zeichne(g);
        }
    }

    public void mousePressed(MouseEvent e) {
        boolean state = ((CheckboxMenuItem) this.getMenuBar().getMenu(1).getItem(0)).getState();
        if (state == false)
            return;

        this.punkt = new Point(e.getX(), e.getY());

        if (this.zeichenmodus == "k") {
            Kreis k = new Kreis(0, 0, e.getX(), e.getY());
            this.figuren.add(k);
        } else if (this.zeichenmodus == "r") {
            Rechteck r = new Rechteck(0, 0, e.getX(), e.getY(), this.keycode);
            this.figuren.add(r);
        }

        this.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        boolean state = ((CheckboxMenuItem) this.getMenuBar().getMenu(1)
                .getItem(0)).getState();
        if (state == false)
            return;

        Figur f = (Figur) figuren.lastElement();
        f.update(e.getX(), e.getY(), this.punkt);

        this.setTitle("x: " + e.getX() + ", y: " + e.getY());
        this.repaint();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        Figur f = (Figur) figuren.lastElement();
        if (e.getKeyCode() == 16) { // Umschalttaste = 16
            f.setSquare(true);
        } else {
            f.setSquare(false);
        }
    }

    public void keyReleased(KeyEvent e) {
        Figur f = (Figur) figuren.lastElement();
        f.setSquare(false || f.alwaysSquare);
    }

    public static void main(String[] args) {
        new Aufgabe1();
    }

    public void actionPerformed(ActionEvent e) {
        String kommando = ((MenuItem) e.getSource()).getActionCommand();

        if (kommando.equals("k") || kommando.equals("r")) {
            this.zeichenmodus = kommando;
        }
        if (kommando.equals("l")) {
            figuren.removeAllElements();
            repaint();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (((CheckboxMenuItem) e.getSource()).getActionCommand() == "v") {
            this.switchVisibility();
        }
    }

    private void switchVisibility() {
        Graphics g = this.getGraphics();
        g.setXORMode(this.getBackground());
        g.setFont(new Font("Arial", Font.PLAIN, 10));

        // Alle Figuren zeichnen
        Iterator it = this.figuren.iterator();
        while (it.hasNext()) {
            Figur f = (Figur) it.next();
            f.zeichne(g);
        }
    }
}