import java.util.*;
//import java.util.ArrayList;

public class Test{
	
	

	public static void main (String[] args) throws Exception {
		ListADT<String> list1 = new SimpleArrayList<String>();
		ListADT<String> list2 = new SimpleArrayList<String>();
		list1.add("A");
		list1.add("B");
		list1.add("C");
		list2.add("X");
		list2.add("Y");
		list2.add("C");
		System.out.println(list1.get(0)+list1.get(1)+list1.get(2));
		System.out.println(list2.get(0)+list2.get(1)+list2.get(2));
		Test.missing(list1, list2);
	}
	
	public static <String> ListADT<String> missing(ListADT<String> list1, ListADT<String> list2) throws Exception{
			ListADT<String> list3 = new SimpleArrayList();
			Iterator itr1 = list1.iterator();
			Iterator itr2 = list2.iterator();
			
			System.out.println(itr1);

			if (list1 == null || list2 == null)
				throw new Exception();
			if (list1.isEmpty() || list2.isEmpty())
				return list3;

		while (itr1.hasNext()){
			boolean findSameItem = false;
			String item1 = (String) itr1.next();

				while (itr2.hasNext() && !findSameItem){
					String item2 = (String) itr2.next();
				
				if (item1.equals(item2))
					findSameItem = true;
			}

			//if (!findSameItem)
				//list3.add(item1);
			itr2 = list2.iterator();
		}

		return list3;
		}

	}
		/*  
		ArrayList<String> tempL = new ArrayList<String>();
		   System.out.println(tempL);
		   ArrayList<String> L1 = 
				   new ArrayList<String>(Arrays.asList("how", "you", "pie", "ate", "now", "you"));
		   ArrayList<String> L2 = 
				   new ArrayList<String>(Arrays.asList("eat", "you", "old", "ate"));
		   for (int i = 0; i < L1.size() && i < L2.size(); i++) {
		      tempL.add(0, L1.remove(0));
		      String temp = L2.remove(0);
		      if ( ! tempL.get(0).equals(temp) ) 
		          tempL.add(0, temp);
		   }
		   System.out.println(tempL);
		   for (int i = 0; i < tempL.size(); i++) {
			  String a = tempL.remove(0);
			  System.out.println(a);
		      L1.add(a);
		   }
		   System.out.println(L1);
		   System.out.println(L2);
		}
		Iterator <String> itr1 = L1.iterator();
		*/
	
	//public static <E> ListADT<E> missing(ListADT<E> list1, ListADT<E> list2)



/*
 * if (list1 == null || list2 == null)
 * 		throw new NullListException();
 * 
 * if (list1.size() == 0)
 * 		newList = list1;
 * else if (list2.size() == 0)
 * 		newList = list2;
 * else{
 * 		// entire print list code implementation
 * }
 * 
 */


