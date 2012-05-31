/**
 * 
 */
package bof.aufgabe2;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Button;

/**
 * @author jt
 * 
 */
@SuppressWarnings("serial")
public class KundeView extends Panel implements DesPattObserver {

    private TextField nummer_field = null;

    private Label nummer_label = null;

    private Label name_label = null;

    private Label vorname_label = null;

    private Label plz_label = null;

    private Label ort_label = null;

    private Label strasse_label = null;

    private Label hausnr_label = null;

    private TextField name_field = null;

    private TextField vorname_field = null;

    private TextField plz_field = null;

    private TextField ort_field = null;

    private TextField strasse_field = null;

    private TextField hsnr_field = null;

    private Kundenliste model;

    private Button speichern_button = null;

    private Button loschen_button = null;

    /**
     * 
     */
    public KundeView(Kundenliste model) {
        super();
        this.model = model;
        model.attach(this);
        initialize();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
        gridBagConstraints21.gridx = 1;
        gridBagConstraints21.gridy = 7;
        GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        gridBagConstraints14.gridx = 0;
        gridBagConstraints14.gridy = 7;
        GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints13.gridy = 6;
        gridBagConstraints13.weightx = 1.0;
        gridBagConstraints13.gridx = 1;
        GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints12.gridy = 5;
        gridBagConstraints12.weightx = 1.0;
        gridBagConstraints12.gridx = 1;
        GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints11.gridy = 4;
        gridBagConstraints11.weightx = 1.0;
        gridBagConstraints11.gridx = 1;
        GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints10.gridy = 3;
        gridBagConstraints10.weightx = 1.0;
        gridBagConstraints10.gridx = 1;
        GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints9.gridy = 2;
        gridBagConstraints9.weightx = 1.0;
        gridBagConstraints9.gridx = 1;
        GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints8.gridy = 1;
        gridBagConstraints8.weightx = 1.0;
        gridBagConstraints8.gridx = 1;
        GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints7.gridy = 6;
        hausnr_label = new Label();
        hausnr_label.setText("Hausnr");
        GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints6.gridy = 5;
        strasse_label = new Label();
        strasse_label.setText("Straße");
        GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints5.gridy = 4;
        ort_label = new Label();
        ort_label.setText("Ort");
        GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints4.gridy = 3;
        plz_label = new Label();
        plz_label.setText("PLZ");
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.gridy = 2;
        vorname_label = new Label();
        vorname_label.setText("Vorname");
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints2.gridy = 1;
        name_label = new Label();
        name_label.setText("Name");
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints1.gridy = 0;
        nummer_label = new Label();
        nummer_label.setText("Kundennummer");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridx = 1;
        this.setSize(400, 400);
        this.setLayout(new GridBagLayout());
        this.add(getNummer_field(), gridBagConstraints);
        this.add(nummer_label, gridBagConstraints1);
        this.add(name_label, gridBagConstraints2);
        this.add(vorname_label, gridBagConstraints3);
        this.add(plz_label, gridBagConstraints4);
        this.add(ort_label, gridBagConstraints5);
        this.add(strasse_label, gridBagConstraints6);
        this.add(hausnr_label, gridBagConstraints7);
        this.add(getName_field(), gridBagConstraints8);
        this.add(getVorname_field(), gridBagConstraints9);
        this.add(getPlz_field(), gridBagConstraints10);
        this.add(getOrt_field(), gridBagConstraints11);
        this.add(getStrasse_field(), gridBagConstraints12);
        this.add(getHsnr_field(), gridBagConstraints13);
        this.add(getSpeichern_button(), gridBagConstraints14);
        this.add(getLoschen_button(), gridBagConstraints21);
    }

    /*
     * (non-Javadoc)
     * 
     * @see gmvc.DesPattObserver#update()
     */
    public void update() {
        repaint();
    }

    /**
     * This method initializes nummer
     * 
     * @return java.awt.TextField
     */
    private TextField getNummer_field() {
        if (nummer_field == null) {
            nummer_field = new TextField();
        }
        return nummer_field;
    }

    /**
     * This method initializes name
     * 
     * @return java.awt.TextField
     */
    private TextField getName_field() {
        if (name_field == null) {
            name_field = new TextField();
        }
        return name_field;
    }

    /**
     * This method initializes nachname
     * 
     * @return java.awt.TextField
     */
    private TextField getVorname_field() {
        if (vorname_field == null) {
            vorname_field = new TextField();
        }
        return vorname_field;
    }

    /**
     * This method initializes plz
     * 
     * @return java.awt.TextField
     */
    private TextField getPlz_field() {
        if (plz_field == null) {
            plz_field = new TextField();
        }
        return plz_field;
    }

    /**
     * This method initializes ort
     * 
     * @return java.awt.TextField
     */
    private TextField getOrt_field() {
        if (ort_field == null) {
            ort_field = new TextField();
        }
        return ort_field;
    }

    /**
     * This method initializes strasse
     * 
     * @return java.awt.TextField
     */
    private TextField getStrasse_field() {
        if (strasse_field == null) {
            strasse_field = new TextField();
        }
        return strasse_field;
    }

    /**
     * This method initializes hsnr
     * 
     * @return java.awt.TextField
     */
    private TextField getHsnr_field() {
        if (hsnr_field == null) {
            hsnr_field = new TextField();
        }
        return hsnr_field;
    }

    @Override
    public void paint(Graphics g) {
        if (this.model.getAktuellenKunden() == null) {
            this.getNummer_field().setText("");
            this.getName_field().setText("");
            this.getVorname_field().setText("");
            this.getPlz_field().setText("");
            this.getOrt_field().setText("");
            this.getStrasse_field().setText("");
            this.getHsnr_field().setText("");
            return;
        }

        this.getNummer_field().setText(
                this.model.getAktuellenKunden().getNummer());
        this.getName_field().setText(this.model.getAktuellenKunden().getName());
        this.getVorname_field().setText(
                this.model.getAktuellenKunden().getVorname());
        this.getPlz_field().setText(
                this.model.getAktuellenKunden().getAdresse().getPlz());
        this.getOrt_field().setText(
                this.model.getAktuellenKunden().getAdresse().getOrt());
        this.getStrasse_field().setText(
                this.model.getAktuellenKunden().getAdresse().getStrasse());
        this.getHsnr_field().setText(
                this.model.getAktuellenKunden().getAdresse().getNr());
    }

    /**
     * This method initializes speichern_button
     * 
     * @return java.awt.Button
     */
    private Button getSpeichern_button() {
        if (speichern_button == null) {
            speichern_button = new Button();
            speichern_button.setLabel("Speichern");
            speichern_button
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if (model.getAktuellePosition() == -1) return;
                            Kunde neuerKunde = new Kunde(getNummer_field()
                                    .getText(), getName_field().getText(),
                                    getVorname_field().getText(), new Adresse(
                                            getHsnr_field().getText(),
                                            getOrt_field().getText(),
                                            getPlz_field().getText(),
                                            getStrasse_field().getText()));
                            // Kunden überschreiben
                            model.setKunde(neuerKunde, model
                                    .getAktuellePosition());
                        }
                    });
        }
        return speichern_button;
    }

    /**
     * This method initializes loschen_button
     * 
     * @return java.awt.Button
     */
    private Button getLoschen_button() {
        if (loschen_button == null) {
            loschen_button = new Button();
            loschen_button.setLabel("Löschen");
            loschen_button
                    .addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent e) {
                            if (model.getAktuellePosition() == -1) return;
                            model.removeKunde(model.getAktuellenKunden());
                        }
                    });
        }
        return loschen_button;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
