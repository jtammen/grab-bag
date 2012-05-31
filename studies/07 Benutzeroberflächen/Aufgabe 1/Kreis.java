import java.awt.Color;
import java.awt.Graphics;

public class Kreis extends Figur {

    public Kreis(int b, int h, int x, int y) {
        super(b, b, x, y, true);
        this.alwaysSquare = true;
    }

    public void zeichne(Graphics g) {
        g.drawOval(this.xpos, this.ypos, this.breite, this.hoehe);
        g.setColor(new Color(220, 220, 220));
        g.drawRect(this.xpos, this.ypos, this.breite, this.hoehe);
        g.drawString("x: " + xpos + ", y: " + ypos + ", h: " + hoehe + ", b: "
                + breite, xpos + 2, ypos - 2);
        g.setColor(Color.BLACK);
    }
}