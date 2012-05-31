package iterator.example;

import java.util.ArrayList;

public abstract class DepotverwaltungV2 {
	protected ArrayList<Element> elemente;

	public DepotverwaltungV2() {
		elemente = new ArrayList<Element>();
	}

	public DepotIterator createIterator() {
		return new DefaultDepotIterator(elemente);
	}

	public final void einfügen(Element o) {
		preEinfügen(o);
		einfügenImpl(o);
		postEinfügen(o);
	}

	public final void suchen(Element o) {
		preSuchen(o);
		suchenImpl(o);
		postSuchen(o);
	}

	public final void löschen(Element o) {
		preLöschen(o);
		löschenImpl(o);
		postLöschen(o);
	}

	private void preEinfügen(Element o) {
		System.out.println("DepotverwaltungV2.preEinfüge()");
	}

	private void postEinfügen(Element o) {
		System.out.println("DepotverwaltungV2.postEinfügen()");
	}

	private void preSuchen(Element o) {
		System.out.println("DepotverwaltungV2.preSuchen()");
	}

	private void postSuchen(Element o) {
		System.out.println("DepotverwaltungV2.einfügenImpl()");
	}

	private void preLöschen(Element o) {
		System.out.println("DepotverwaltungV2.preLöschen()");
	}

	private void postLöschen(Element o) {
		System.out.println("DepotverwaltungV2.postLöschen()");
	}

	protected abstract void einfügenImpl(Element o);

	protected abstract void suchenImpl(Element o);

	protected abstract void löschenImpl(Element o);
}