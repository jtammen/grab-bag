package client;


public class GFKundeEintragen implements KundeSaveListener {

	Kundendarstellung view;

	Kunde kunde;

	public GFKundeEintragen(Kundendarstellung view, Kunde kunde) {
		view.addSaveListener(this);
		this.view = view;
		this.kunde = kunde;
	}

	public void savePerformed() {
		System.out.println("GFKundeEintragen.savePerformed()");

		String adresse = view.getAdresseField().getText();
		if (!adresse.equals(""))
			kunde.setAdresse(adresse);

		String kundennummer = view.getKundennummerField().getText();
		if (!kundennummer.equals("")) {
			try {
				kunde.setKundennummer(Integer.parseInt(kundennummer));
			} catch (NumberFormatException e) {
				kunde.setKundennummer(0);
			}
		}

		String name = view.getNameField().getText();
		if (!name.equals(""))
			kunde.setName(name);

		kunde.save();
	}
}