package il.ac.tau.cs.sw1.hw6;

public class SectionB {
	
	/*
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) {
		if(array == null){
			return false;
		}
		for (int i=0; i< array.length; i ++){
			if(array[i] == value){
				return true;
			}
		}
		return false;
	}
	
	// there is intentionally no @post condition here 
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int unknown(int[] array) { 

		return 0;
	}
	/*
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] <= $ret
	*/
	public static int max(int[] array) {
		return array[array.length -1];
	}
	
	/*
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min(int[] array) { 
		int minValue = Integer.MAX_VALUE;
		for(int i=0; i< array.length; i++){
			if(minValue >= array[i]){
				minValue = array[i];
			}
		}
		return minValue;
	}
	
	/*
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(word.length() - i - 1)

	*/
	public static String reverse(String word) 
	{
		String revWord = "";
		for(int i = word.length() - 1; i >= 0; i--){
			revWord = revWord + word.charAt(i);
		}
		return revWord;
	}
	
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) {
		int i= 0, j=0;
		while(array[i] == array[j]){
			j++;
		}
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}


}
