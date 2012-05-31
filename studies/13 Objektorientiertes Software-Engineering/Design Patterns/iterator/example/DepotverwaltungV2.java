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

	public final void einf�gen(Element o) {
		preEinf�gen(o);
		einf�genImpl(o);
		postEinf�gen(o);
	}

	public final void suchen(Element o) {
		preSuchen(o);
		suchenImpl(o);
		postSuchen(o);
	}

	public final void l�schen(Element o) {
		preL�schen(o);
		l�schenImpl(o);
		postL�schen(o);
	}

	private void preEinf�gen(Element o) {
		System.out.println("DepotverwaltungV2.preEinf�ge()");
	}

	private void postEinf�gen(Element o) {
		System.out.println("DepotverwaltungV2.postEinf�gen()");
	}

	private void preSuchen(Element o) {
		System.out.println("DepotverwaltungV2.preSuchen()");
	}

	private void postSuchen(Element o) {
		System.out.println("DepotverwaltungV2.einf�genImpl()");
	}

	private void preL�schen(Element o) {
		System.out.println("DepotverwaltungV2.preL�schen()");
	}

	private void postL�schen(Element o) {
		System.out.println("DepotverwaltungV2.postL�schen()");
	}

	protected abstract void einf�genImpl(Element o);

	protected abstract void suchenImpl(Element o);

	protected abstract void l�schenImpl(Element o);
}