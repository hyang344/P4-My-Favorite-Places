import java.util.*;

public class SimpleArrayListIterator implements Iterator<String>{

	private SimpleArrayList<String> myList;
	private int currPos;
	
	public SimpleArrayListIterator(SimpleArrayList<String> list){
		myList = list;
		currPos = 0;
	}
	
	public boolean hasNext() {
		// TODO Auto-generated method stub
		System.out.println(currPos + " " + myList.size());
		return currPos < myList.size();
	}

	public String next() {
		// TODO Auto-generated method stub
		if (!hasNext())
			throw new NoSuchElementException();
		String result = myList.get(currPos);
		currPos++;
		return result;
	}

	

}
