import java.awt.Color;
import java.awt.Graphics;

public class Rechteck extends Figur {

    Rechteck(int b, int h, int x, int y, boolean square) {
        super(b, h, x, y, square);
    }

    public void zeichne(Graphics g) {
        g.drawRect(this.xpos, this.ypos, this.breite, this.hoehe);
        g.setColor(new Color(220, 220, 220));
        g.drawString("x: " + xpos + ", y: " + ypos + ", h: " + hoehe + ", b: "
                + breite, xpos + 2, ypos - 2);
        g.setColor(Color.BLACK);
    }
}