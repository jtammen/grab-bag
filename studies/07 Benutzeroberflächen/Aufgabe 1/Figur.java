import java.awt.Point;

public abstract class Figur {
    protected int breite, hoehe, xpos, ypos;
    protected boolean square = false;
    protected boolean alwaysSquare = false;

    Figur(int b, int h, int x, int y, boolean square) {
        this.breite = b;
        this.hoehe = h;
        this.xpos = x;
        this.ypos = y;
        this.square = square;
    }

    public int getBreite() {
        return this.breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public int getHoehe() {
        return this.hoehe;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public int getXpos() {
        return this.xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return this.ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public boolean isSquare() {
        return square;
    }

    public void setSquare(boolean square) {
        this.square = square;
    }
    
    /**
     * Zeichne die Figur auf die Grafik-Oberfläche.
     * 
     * @param   g   Grafik-Objekt
     */
    abstract public void zeichne(java.awt.Graphics g);

    /**
     * Aktualisiert die X-/Y-Koordinaten sowie Breite und Höhe der Figur. 
     * 
     * Dabei werden vier Fälle (1.-4. Quadrant) unterschieden. Wenn die
     * Figur in ihrer Höhe und Breite gleich sein soll (z.B. Kreis), so
     * müssen bei manchen Fällen Korrekturfaktoren ermittelt werden, damit
     * die Figur immer an ihrem Ursprungspunkt hängen bleibt und nicht
     * "verschoben" wird.
     * 
     * @param   x       die aktuelle X-Position (MouseEvent)
     * @param   y       die aktuelle Y-Position
     * @param   punkt   der Ursprungspunkt der Figur
     */
    public void update(int x, int y, Point punkt) {
        if (x > punkt.x && y < punkt.y) {           // 1. Quadrant
            this.breite = x - punkt.x;
            this.hoehe = punkt.y - y;
            this.ypos = y;
            this.xpos = punkt.x;

            if (this.square == true) {
                // Korrekturfaktor für die Y-Position falls die 
                // Figur breiter als höher ist
                int dhb = this.hoehe - this.breite;
                if (dhb < 0) {
                    this.hoehe = this.breite;
                    this.ypos += dhb;
                } else {
                    this.breite = this.hoehe;
                }
            }

        } else if (x < punkt.x && y < punkt.y) {    // 2. Quadrant
            this.breite = punkt.x - x;
            this.hoehe = punkt.y - y;
            this.xpos = x;
            this.ypos = y;

            if (this.square == true) {
                // Korrekturfaktor für die X- und Y-Positionen
                int dhb = this.hoehe - this.breite;
                if (dhb < 0) {
                    this.hoehe = this.breite;
                    this.ypos += dhb;
                } else {
                    this.breite = this.hoehe;
                    this.xpos -= dhb;
                }
            }            
            
        } else if (x < punkt.x && y > punkt.y) {    // 3. Quadrant
            this.breite = punkt.x - x;
            this.hoehe = y - punkt.y;
            this.xpos = x;
            this.ypos = punkt.y;

            // System.out.println("h/b: " + this.hoehe + "," + this.breite);
            // System.out.println("xpos: " + this.xpos);

            if (this.square == true) {
                // Korrekturfaktor für die Y-Position falls die 
                // Figur höher als breiter ist
                int dhb = this.hoehe - this.breite;
                if (dhb < 0) {
                    this.hoehe = this.breite;
                } else {
                    this.breite = this.hoehe;
                    this.xpos -= dhb;
                }
            }

        } else if (x > punkt.x && y > punkt.y) {    // 4. Quadrant
            this.breite = x - punkt.x;
            this.hoehe = y - punkt.y;
            this.xpos = punkt.x;
            this.ypos = punkt.y;

            if (this.square == true) {
                // Höhe und Breite gleichsetzen
                this.breite = this.hoehe = Math.max(this.hoehe, this.breite);
            }
        }
    }
}
