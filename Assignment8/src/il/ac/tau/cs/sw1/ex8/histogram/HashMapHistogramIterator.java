package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class HashMapHistogramIterator<T extends Comparable<T>> 
							implements Iterator<Map.Entry<T, Integer>>{
	
	//add members here
	HashMap hashMap;
	Iterator<Map.Entry<T, Integer>> iterator;

	public HashMapHistogramIterator (HashMap hashMap){

		this.hashMap = hashMap;
		this.iterator = this.hashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).iterator();

	}

	//add constructor here, if needed
	
	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public Map.Entry<T, Integer> next() {
		if (hasNext()){
			return this.iterator.next();
		}
		return null; //replace this with the correct value
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
	}
	
	//add private methods here, if needed

}
