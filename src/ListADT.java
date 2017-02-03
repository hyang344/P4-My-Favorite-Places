import java.util.Iterator;
import java.util.*;

public interface ListADT<T> {
	public void add (String item);
	Iterator<String> iterator();
	boolean isEmpty();
	public String get(int i);

}
