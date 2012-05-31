package composite;

import java.util.Iterator;

public class Leaf extends Component {
	@Override
	public Iterator<Component> getChildIterator() {
		return null;
	}
}