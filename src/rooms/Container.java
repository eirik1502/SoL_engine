package rooms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Container<T> implements Iterable<T> {

	
	private List<T> objects = new ArrayList<>();
	
	
	public void add( T object ) {

		objects.add(object);
	}
	public void remove( T object ) {
		objects.remove(object);
	}
	
	public int size() {
		return objects.size();
	}
	
	public T get(int i) {
		return objects.get(i);
	}
	
	@Override
	public Iterator<T> iterator() {
		return objects.iterator();
	}
	
}
