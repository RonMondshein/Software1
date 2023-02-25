package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.*;
import java.util.stream.Collectors;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{

	HashMap<T, Integer> hashMap;
	
	
	//add constructor here, if needed

	
	public HashMapHistogram(){
		hashMap = new HashMap<T, Integer>();
	}
	
	@Override
	public void addItem(T item) {
		if(hashMap.containsKey(item)){
			hashMap.put(item, hashMap.get(item) + 1);
		} else{
			hashMap.put(item, 1);
		}
	}
	
	@Override
	public boolean removeItem(T item)  {
		if(hashMap.containsKey(item)){
			if(hashMap.get(item) > 1){
				hashMap.put(item, hashMap.get(item) - 1);
			} else{
				hashMap.remove(item);
			}
			return  true;
		}
		return false;
	}
	
	@Override
	public void addAll(Collection<T> items) {
		for(T item: items){
			addItem(item);
		}
	}

	@Override
	public int getCountForItem(T item) {
		if(hashMap.containsKey(item)){
			return hashMap.get(item);
		}
		return 0;
	}

	@Override
	public void clear() {
		hashMap.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return hashMap.keySet();
	}
	
	@Override
	public int getCountsSum() {
		int sum = 0;
		for(Integer times: hashMap.values()){
			sum += times;
		}
		return sum;
	}

	@Override
	public Iterator<Map.Entry<T, Integer>> iterator() {
		return new HashMapHistogramIterator<T>(this.hashMap);
	}
	
	//add private methods here, if needed
}
