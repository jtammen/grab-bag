package composite.example;

import java.util.Iterator;

public class Datei extends DateisystemKomponente {
	private int size;

	public Datei(int i) {
		size = i;
	}

	@Override
	public Iterator<DateisystemKomponente> getChildIterator() {
		return null;
	}

	@Override
	public int getGröße() {
		return size;
	}
}