package bof.aufgabe2;
import java.util.*;

public abstract class DesPattSubject {
	private Vector<DesPattObserver> observers = new Vector<DesPattObserver>();
	
	public void attach(DesPattObserver o){
        observers.addElement(o);
	} 
	
	public void despattNotify(){
		Vector ob;
		synchronized (this){
			ob = (Vector) observers.clone();
		}
		for (int i = 0; i < ob.size(); i++)
			((DesPattObserver) ob.elementAt(i)).update();
	}
	
	public void detach(DesPattObserver o){
        observers.removeElement(o);
	}
}