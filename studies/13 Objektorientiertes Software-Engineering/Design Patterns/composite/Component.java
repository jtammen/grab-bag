package composite;

import java.util.Iterator;

public abstract class Component {
	public abstract Iterator<Component> getChildIterator();
}