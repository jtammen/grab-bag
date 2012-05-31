package composite;

import java.util.Iterator;
import java.util.Vector;

public class Composite extends Component {
	private Vector<Component> children;

	public Composite() {
		children = new Vector<Component>();
	}

	@Override
	public Iterator<Component> getChildIterator() {
		return children.iterator();
	}

	public void add(Component c) {
		if (this.equals(c))
			return;
		children.add(c);
	}

	public void remove(Component c) {
		children.remove(c);
	}
}