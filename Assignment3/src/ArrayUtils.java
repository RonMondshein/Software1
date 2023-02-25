import java.util.Arrays;

public class ArrayUtils {

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		int arrLength = array.length;
		int firstI = 0, place = 0, index = 0;
		int[] copiedArr = new int[arrLength];
		copiedArr = Arrays.copyOf(array, arrLength);
		if ((direction != 'R' && direction != 'L') || move == 0 || arrLength == 0) {
			// move=0 or direction != 'R' OR 'L' or empty arr
			return array;
		} else if ((direction == 'R' && move > 0) || (direction == 'L' && move < 0)) {
			// move the array to the right
			firstI = Math.abs(move) % arrLength;
		} else if ((direction == 'L' && move > 0) || (direction == 'R' && move < 0)) {
			// move the arr to the left
			firstI = arrLength - Math.abs(move) % arrLength;
		} else {
			// to get rid of any problem
			return array;
		}
		while (place < arrLength) {
			array[firstI] = copiedArr[index];
			index++;
			place++;
			firstI++;
			if (firstI >= arrLength) {
				firstI = 0;
			}
		}
		return array;
	}

	public static int minPath;
	public static int findShortestPath(int[][] m, int i, int j) {
		minPath = Integer.MAX_VALUE;
		if(i==j){
			//from edge to himself
			return 0;
		}

		shortPathRec(m, i, j, 0, i);
		if(minPath == Integer.MAX_VALUE){
			return -1;
		} else {
			return minPath;
		}

	}

	public  static void shortPathRec(int[][] m, int i, int j, int len, int current) {
		if ((len < m.length + 2)){
		for (int k = 0; k < m.length; k++){
			if (i != j ){
				if (m[current][k]!=0) {

					if (k == j) {
						if (minPath == Integer.MAX_VALUE){
							minPath = len + 1;
						} else {
							minPath = Math.min(minPath, len + 1);
						}
						} else{
							shortPathRec(m, i, j, len + 1, k);}	}	}	}	}

		
	}

}
