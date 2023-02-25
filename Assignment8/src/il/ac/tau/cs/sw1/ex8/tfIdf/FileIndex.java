package il.ac.tau.cs.sw1.ex8.tfIdf;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	private boolean isInitialized = false;
	private HashMap<String, HashMapHistogram> filesMap = new HashMap<>();
	private HashMapHistogram hashMapHistogram;
	List<ArrayList<String>> arrangeAllFiles;

	List<String> filesNames;

	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 * @pre: isInitialized() == false;
	 */
  	public void indexDirectory(String folderPath) { //Q1
		//This code iterates over all the files in the folder. add your code wherever is needed

		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder

			if (file.isFile()) {
				try {
					List<String> wordsList = FileUtils.readAllTokens(file);
					hashMapHistogram = new HashMapHistogram();
					hashMapHistogram.addAll(wordsList);
					filesMap.put(file.getName(), hashMapHistogram);

				} catch (IOException exception) {
					throw new RuntimeException(exception);
				}
			}
		}
		arrangeAllFiles = new ArrayList<ArrayList<String>>();

		filesNames = new ArrayList<>();
		for (Map.Entry<String, HashMapHistogram> file : filesMap.entrySet()) {
			arrangeAllFiles.add(new ArrayList<>());
			filesNames.add(file.getKey());
		}
		String temp = "";
		for (int k = 0; k < filesNames.size(); k++) {
			try {
				List<String> wordsTemp = filesMap.get(filesNames.get(k)).getItemsSet().stream().toList();
				ArrayList<String> words = arrangeAllFiles.get(k);
				for (int i = 0; i < wordsTemp.size(); i++) {
					words.add(i, wordsTemp.get(i));
				}
				Collections.sort(words);
				for (int i = 0; i < words.size() - 1; i++) {
					for (int j = i + 1; j < words.size(); j++) {
						double idf1 = getTFIDF(words.get(i), filesNames.get(k));
						double idf2 = getTFIDF(words.get(j), filesNames.get(k));
						if (idf1 < idf2 || ((idf1 == idf2) && (words.get(i).compareToIgnoreCase(words.get(j)) > 0))) {
							temp = words.get(j);
							words.set(j, words.get(i));
							words.set(i, temp);
						}
					}
				}
			} catch (FileIndexException e) {
				throw new RuntimeException(e);
			}

		}
		isInitialized = true;
	}
	
	
	
	// Q2
  	
	/* @pre: isInitialized() */
	public int getCountInFile(String word, String fileName) throws FileIndexException{
		if(filesMap.containsKey(fileName)) {
			return filesMap.get(fileName).getCountForItem(word.toLowerCase());
		}
		throw new FileIndexException("getCountInFile File doesn't exist");

	}
	
	/* @pre: isInitialized() */
	public int getNumOfUniqueWordsInFile(String fileName) throws FileIndexException{
		if(filesMap.containsKey(fileName)) {
			return filesMap.get(fileName).getItemsSet().size();
		}
		throw new FileIndexException("getNumOfUniqueWordsInFile File doesn't exist");
	}
	
	/* @pre: isInitialized() */
	public int getNumOfFilesInIndex(){
		return filesMap.size();
	}

	
	/* @pre: isInitialized() */
	public double getTF(String word, String fileName) throws FileIndexException{ // Q3

		return calcTF(getCountInFile(word.toLowerCase(), fileName), filesMap.get(fileName).getCountsSum());
	}
	
	/* @pre: isInitialized() 
	 * @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getIDF(String word){ //Q4
		int docsContainingWord = 0;
		for(Map.Entry<String, HashMapHistogram> file : filesMap.entrySet()) {
			if(file.getValue().getCountForItem(word.toLowerCase()) > 0){
				docsContainingWord += 1;
			}
		}
		if (docsContainingWord == 0 && getNumOfFilesInIndex() == 0){
			return calcIDF(1, 1);

		}
		if(docsContainingWord == 0){
			docsContainingWord = 1;
		}
		return calcIDF(getNumOfFilesInIndex(),docsContainingWord); //replace this with the correct value
	}
	
	
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfUniqueWordsInFile(fileName)
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKMostSignificantWords(String fileName, int k) 
													throws FileIndexException{ //Q5
		int currentIndex = filesNames.indexOf(fileName);
		ArrayList<Map.Entry<String, Double>> mostsignWords = new ArrayList<>();
		if(k > arrangeAllFiles.get(currentIndex).size() ){
			return null;
		}
		for(int i = 0; i < k; i++){
			double idf = getTFIDF(arrangeAllFiles.get(currentIndex).get(i), fileName);
			Map.Entry<String, Double> temp = Map.entry(arrangeAllFiles.get(currentIndex).get(i), idf);
			mostsignWords.add(i,temp);

		}
		return mostsignWords;
	}
	
	/* @pre: isInitialized() */
	public double getCosineSimilarity(String fileName1, String fileName2) throws FileIndexException{ //Q6
		int index1 = filesNames.indexOf(fileName1);
		int index2 = filesNames.indexOf(fileName2);
		ArrayList<String> union = new ArrayList<>();
		for (String x : arrangeAllFiles.get(index1)){
			union.add(x);
		}
		for (String x : arrangeAllFiles.get(index2)){
			if (!union.contains(x))
				union.add(x);
		}

		double up = 0, down = 0;
		double temp1 = 0, temp2 = 0, temp = 0;
		for (int i = 0; i < union.size(); i++){
			if(arrangeAllFiles.get(index2).contains(union.get(i)) && arrangeAllFiles.get(index1).contains(union.get(i))){
				double idf1 = getTFIDF(union.get(i), fileName1);
				double idf2 = getTFIDF(union.get(i), fileName2);
				up += idf1 * idf2;
			}
			if(arrangeAllFiles.get(index1).contains(union.get(i))){
				double x = getTFIDF(union.get(i), fileName1);
				temp1 += Math.pow(getTFIDF(union.get(i), fileName1), 2);
			}
			if(arrangeAllFiles.get(index2).contains(union.get(i))){
				double x = getTFIDF(union.get(i), fileName2);
				temp2 += Math.pow(getTFIDF(union.get(i), fileName2), 2);
			}


		}
		temp = temp1 * temp2;
		if(down == 0 ){
			down = 1;

		}
		down = Math.sqrt(temp);
		return up/down;
	}
	
	/*
	 * @pre: isInitialized()
	 * @pre: 0 < k <= getNumOfFilesInIndex()-1
	 * @post: $ret.size() = k
	 * @post for i in (0,k-2):
	 * 		$ret[i].value >= $ret[i+1].value
	 */
	public List<Map.Entry<String, Double>> getTopKClosestDocuments(String fileName, int k) 
			throws FileIndexException{ //Q6
		List<Double> coslist = new ArrayList<>();
		List<String> namelist = new ArrayList<>();

		for (String name: filesNames ){
			if (!fileName.equals(name)){
				coslist.add(getCosineSimilarity(fileName, name));
				namelist.add(name);
			}
		}
		Double tempD = 0.0;
		String tempS = "";
		for (int i = 0; i < filesNames.size() - 2; i++) {
			for (int j = i + 1; j < filesNames.size()-1; j++) {
				if (coslist.get(i) < coslist.get(j)) {
					tempD = coslist.get(j);
					tempS = namelist.get(j);
					coslist.set(j, coslist.get(i));
					coslist.set(i, tempD);
					namelist.set(j, namelist.get(i));
					namelist.set(i, tempS);
				}
			}
		}

		ArrayList<Map.Entry<String, Double>> topK =  new ArrayList<>();
		for(int i = 0 ; i < k; i++){
			Map.Entry<String, Double> temp = Map.entry(namelist.get(i), coslist.get(i));
			topK.add(temp);
		}
		return topK; 
	}

	
	
	//add private methods here, if needed

	
	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	
	public boolean isInitialized(){
		return this.isInitialized;
	}
	
	/* @pre: exist fileName such that getCountInFile(word) > 0*/
	public double getTFIDF(String word, String fileName) throws FileIndexException{
		return this.getTF(word, fileName)*this.getIDF(word);
	}
	
	private static double calcTF(int repetitionsForWord, int numOfWordsInDoc){
		return (double)repetitionsForWord/numOfWordsInDoc;
	}
	
	private static double calcIDF(int numOfDocs, int numOfDocsContainingWord){
		return Math.log((double)numOfDocs/numOfDocsContainingWord);
	}
	
}
