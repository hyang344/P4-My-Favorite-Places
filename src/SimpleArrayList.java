import java.util.Iterator;
import java.util.*;

public class SimpleArrayList<T> implements ListADT<String>, Iterable<String> {

	private String[] items;
	private int numItem;
	
	public SimpleArrayList(){
		items = new String[100];
		numItem = 0;
	}
	
	@Override
	public void add(String item) {
		// TODO Auto-generated method stub
		items[numItem] = item;
		numItem++;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size() == 0;
	}

	public int size() {
		// TODO Auto-generated method stub
		return numItem;
	}

	public String get(int currPos) {
		// TODO Auto-generated method stub
		return items[currPos];
	}

}
