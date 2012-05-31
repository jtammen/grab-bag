package composite.example;

import java.util.Iterator;

public abstract class DateisystemKomponente {
	public abstract Iterator<DateisystemKomponente> getChildIterator();

	public abstract int getGröße();
}