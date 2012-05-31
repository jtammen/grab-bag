package iterator.example;

public interface DepotIterator {
	public Element first();

	public Element next();

	public Element current();

	public boolean isDone();
}