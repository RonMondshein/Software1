
public class StringUtils {

	public static String findSortedSequence(String str) {
		if(str.isBlank()){
			return "";
		}
		String[] words = str.split(" ");
		int currentLen = 1, first = 0, len = 1;
		for (int i = 0; i < words.length - 1; i++) {
			if(words[i].compareToIgnoreCase(words[i+1]) <= 0){
				currentLen ++;
			} else{
				if(currentLen >= len){
					len =currentLen;
					first = i - len + 1;
					currentLen = 1;
				}else {
					currentLen = 1;
				}
			}
			if((i == words.length - 2) && (currentLen >= len)){
				len =currentLen;
				first = i - len + 2; }
		}

		if (len == 0) {
			return words[words.length - 1];
		} else {
			String ans = words[first];
			for (int j = first + 1; j < len + first ; j++) {
				ans = String.join(" ", ans, words[j]);
			}
			return ans;
		}

	}

	
	public static boolean isEditDistanceOne(String a, String b){
		int lenA = a.length(), lenB = b.length(), countErrors = 0;
		String small, big;
		if (Math.abs(lenA - lenB) > 1){
			return false;
		}
		if (lenA == lenB){
			for (int i = 0; i < lenA; i ++){
				if(a.charAt(i) != b.charAt(i)){
					countErrors++;
				}
			}
		} else {
			if (lenA < lenB) {
				small = a;
				big = b;
			} else {
				small = b;
				big = a;
			}
			for (int i = 0, j = 0; i < small.length(); i++, j++) {
				if (small.charAt(i) != big.charAt(j)) {
					countErrors++;
					j++;
					if (small.charAt(i) != big.charAt(j)) {
						countErrors++;
					}
					if (countErrors >= 2){
						return false;
					}
				}
			}

		}
			if(countErrors <= 1){
				return true;
			} return  false;
	}
	
}
