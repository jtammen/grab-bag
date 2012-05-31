package composite.example;

import java.util.Iterator;
import java.util.Vector;

public class Ordner extends DateisystemKomponente {
	private Vector<DateisystemKomponente> children;

	public Ordner() {
		children = new Vector<DateisystemKomponente>();
	}

	@Override
	public int getGröße() {
		int size = 0;

		Iterator<DateisystemKomponente> it = getChildIterator();
		while (it.hasNext()) {
			DateisystemKomponente element = it.next();
			size += element.getGröße();
		}

		return size;
	}

	@Override
	public Iterator<DateisystemKomponente> getChildIterator() {
		return children.iterator();
	}

	public void add(DateisystemKomponente c) {
		if (this.equals(c))
			return;
		children.add(c);
	}

	public void remove(DateisystemKomponente c) {
		children.remove(c);
	}
}