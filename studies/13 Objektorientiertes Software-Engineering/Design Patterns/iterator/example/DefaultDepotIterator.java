package iterator.example;

import java.util.ArrayList;

public class DefaultDepotIterator implements DepotIterator {
	private int pos;

	private ArrayList<Element> elemente;

	public DefaultDepotIterator(ArrayList<Element> elemente) {
		pos = 0;
		this.elemente = elemente;
	}

	public Element current() {
		// TODO Auto-generated method stub
		return null;
	}

	public Element first() {
		return elemente.get(0);
	}

	public boolean isDone() {
		return pos == elemente.size();
	}

	public Element next() {
		return elemente.get(pos++);
	}
}
