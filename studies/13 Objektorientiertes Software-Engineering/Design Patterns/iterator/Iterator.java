package iterator;

public abstract class Iterator {
	private Aggregat aggregat;

	public Iterator(Aggregat aggregat) {
		super();
		this.aggregat = aggregat;
	}
	
	public abstract Object first();
	public abstract Object next();
	public abstract Object current();
	public abstract boolean isDone();
}