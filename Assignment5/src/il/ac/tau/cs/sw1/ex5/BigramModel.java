package il.ac.tau.cs.sw1.ex5;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;

	String[] mVocabulary;
	int[][] mBigramCounts;

	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException {
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);

	}



	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */

	public boolean isAlreadyThere(String[] words, String word, int numOfWords) {
		for (int i = 0; i < numOfWords; i++) {
			if (words[i].equals(word)) {
				return true;
			}
		}
		return false;
	}

	public String[] buildVocabularyIndex(String fileName) throws IOException { // Q 1
		// replace with your code
		String text = "";
		int numOfWords = 0;
		char[] lettersArray;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {
				text = text + " " + strCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String word = "";
		boolean isChar = false, isnotCharOrDigit = false, someNumInArray = false;
		String[] vocabularyarray = new String[MAX_VOCABULARY_SIZE];
		for (int i = 0; i < text.length(); i++) {
			word = "";
			while (text.charAt(i) != ' ') {
				word = word + text.charAt(i);
				if (i < text.length() - 1) {
					i++;
				} else {
					break;
				}
			}

			lettersArray = word.toLowerCase().toCharArray();
			for (int j = 0; j < lettersArray.length; j++) {
				if ((int) (lettersArray[j]) >= 97 && (int) (lettersArray[j]) <= 122) {
					isChar = true;
				} else if (!((int) (lettersArray[j]) >= 48 && (int) (lettersArray[j]) <= 57)) {
					isnotCharOrDigit = true;
				}
			}
			if (numOfWords >= MAX_VOCABULARY_SIZE) {
				break;
			}
			if (isChar) {
				if (!isAlreadyThere(vocabularyarray, word.toLowerCase(), numOfWords)) {
					vocabularyarray[numOfWords] = word.toLowerCase();
					numOfWords++;
				}
			} else if (!isnotCharOrDigit && word != "") {
				if (!someNumInArray) {
					vocabularyarray[numOfWords] = SOME_NUM;
					someNumInArray = true;
					numOfWords++;
				}
			}
			isChar = false;
			isnotCharOrDigit = false;
		}
		return Arrays.copyOf(vocabularyarray, numOfWords);
	}

	public int placeInVocabulary(String[] vocabulary, String word) {
		for (int i = 0; i < vocabulary.length; i++) {
			if (vocabulary[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}
	public boolean isnumber(String word){
		for (int i = 0; i < word.length(); i++){
			if(!Character.isDigit(word.charAt(i))){
				return false;
			}
		}
		return true;
	}

	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException { // Q - 2
		// replace with your code
		int[][] bigramCounts = new int[vocabulary.length][vocabulary.length];
		String text = "";
		String word1 = "", word2 = "";
		int find1 = -1, find2 = -1;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {
				/////////////
				word1 = "";
				word2 = "";
				for (int i = 0; i < strCurrentLine.length(); i++) {
					word2 = "";
					while (strCurrentLine.charAt(i) != ' ') {
						word2 = word2 + strCurrentLine.charAt(i);
						if (i < strCurrentLine.length() - 1) {
							i++;
						} else {
							break;
						}
					}

					if (word1 != "" && word2 != "") {
						find1 = placeInVocabulary(vocabulary, word1.toLowerCase());
						find2 = placeInVocabulary(vocabulary, word2.toLowerCase());
						if (find1 == -1){
							if(isnumber(word1))
								find1 = placeInVocabulary(vocabulary, SOME_NUM);
						}
						if (find2 == -1){
							if(isnumber(word2))
								find2 = placeInVocabulary(vocabulary, SOME_NUM);
						}
						if (find1 != -1 && find2 != -1) {
							bigramCounts[find1][find2]++;
						}
					}
					word1 = word2;
				}
			}
			////////////////

		} catch (IOException e) {
			e.printStackTrace();
		}
		return bigramCounts;

	}


	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException { // Q-3
		try {
			FileWriter myWriter = new FileWriter(fileName + VOC_FILE_SUFFIX);
			myWriter.write(mVocabulary.length + " words\n");
			for (int i = 0; i < mVocabulary.length; i++) {
				myWriter.write(i + "," + mVocabulary[i] + "\n");
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter(fileName + COUNTS_FILE_SUFFIX);
			for (int k = 0; k < mBigramCounts.length; k++) {
				for (int j = 0; j < mBigramCounts.length; j++) {
					if (mBigramCounts[k][j] > 0) {
						myWriter.write(k + "," + j + ":" + mBigramCounts[k][j] + "\n");
					}
				}

			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}


	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException { // Q - 4
		int words = -1;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName + VOC_FILE_SUFFIX))) {
			String strCurrentLine;
			boolean firstLoop = true;
			while ((strCurrentLine = br.readLine()) != null) {
				if (words == -1) {
					int j = 0;
					while (strCurrentLine.charAt(j) != ' ') {
						j++;
					}
					words = Integer.parseInt(strCurrentLine.substring(0, j));
					mVocabulary = new String[words];
				}

				if (!firstLoop) {
					int j = 0;
					while (strCurrentLine.charAt(j) != ',') {
						j++;
					}
					mVocabulary[Integer.parseInt(strCurrentLine.substring(0, j))] = strCurrentLine.substring(j + 1);

				}
				firstLoop = false;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		mBigramCounts = new int[words][words];
		try (BufferedReader br = new BufferedReader(new FileReader(fileName + COUNTS_FILE_SUFFIX))) {
			String strCurrentLine;
			while ((strCurrentLine = br.readLine()) != null) {
				int i = 0;
				int j = 0;
				while (strCurrentLine.charAt(i) != ',') {
					i++;
				}
				while (strCurrentLine.charAt(j) != ':') {
					j++;
				}
				mBigramCounts[Integer.parseInt(strCurrentLine.substring(0, i))][Integer.parseInt(strCurrentLine.substring(i + 1, j))] =
						Integer.parseInt(strCurrentLine.substring(j + 1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word) {  // Q - 5
		// replace with your code
		for (int i = 0; i < mVocabulary.length; i++) {
			if (mVocabulary[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}


	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2) { //  Q - 6
		// replace with your code
		int i1 = getWordIndex(word1), i2 = getWordIndex(word2);
		if (i1 == -1 || i1 == -1) {
			return 0;
		}
		return mBigramCounts[i1][i2];
	}


	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word) { //  Q - 7
		// replace with your code
		int max = -1, maxI = -1;
		int wordI = getWordIndex(word);
		for (int i = 0; i < mBigramCounts.length; i++) {
			if (mBigramCounts[wordI][i] > max && mBigramCounts[wordI][i] != 0) {
				max = mBigramCounts[wordI][i];
				maxI = i;
			}
		}
		if (max == -1) {
			return null;
		}
		return mVocabulary[maxI];
	}


	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence) {  //  Q - 8
		// replace with your code
		String[] arrOfStr = sentence.split(" ");
		int word1I = -1, word2I = -1;
		for (int i = 0; i < arrOfStr.length - 1; i++) {
			word1I = getWordIndex(arrOfStr[i]);
			word2I = getWordIndex(arrOfStr[i + 1]);
			if (word2I == -1 || word1I == -1) {
				return false;
			}
			if (mBigramCounts[word1I][word2I] < 1) {
				return false;
			}
		}
		int wordI = getWordIndex(arrOfStr[arrOfStr.length - 1]);
		if (wordI == -1) {
			return false;
		}

		return true;
	}


	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2) { //  Q - 9
		// replace with your code
		boolean onlyZero1 = true, onlyZero2 = true;
		double up = 0, down = 1;
		double sum1 = 0, sum2 = 0, sqr1 = 0, sqr2 = 0;
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != 0) {
				onlyZero1 = false;
			}
			if (arr2[i] != 0) {
				onlyZero2 = false;
			}
			up = up + arr1[i] * arr2[i];
			sum1 = sum1 + arr1[i] * arr1[i];
			sum2 = sum2 + arr2[i] * arr2[i];
		}
		sqr1 = Math.sqrt(sum1);
		sqr2 = Math.sqrt(sum2);
		if (onlyZero1 || onlyZero2) {
			return -1;
		}
		return up / (sqr1 * sqr2);

	}


	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized),
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word) { //  Q - 10
		// replace with your code

		if (mBigramCounts.length == 1) {
			return mVocabulary[0];
		}
		int wordIndex = getWordIndex(word);
		int[] wordVector = mBigramCounts[wordIndex];
		int[] similarVector, tempVector;
		double max = -1.0;
		int maxI = -1;
		for (int i = 0; i < mBigramCounts.length; i++) {
			if (i != wordIndex) {
				tempVector = mBigramCounts[i];
				if (calcCosineSim(wordVector, tempVector) > max) {
					maxI = i;
					max = calcCosineSim(wordVector, tempVector);
				}
			}
		}
		return mVocabulary[maxI];
	}
}
