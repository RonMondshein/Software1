package il.ac.tau.cs.sw1.ex4;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';

	/*
	 * @pre: template is legal for word
	 */
	public static char[] createPuzzleFromTemplate(String word, boolean[] template) { // Q - 1
		char[] puzzleWord = new char[template.length];
		for (int i = 0; i < template.length; i ++ ) {
			if (template[i] == true ) {
				puzzleWord[i] = HIDDEN_CHAR;
			} else {
				puzzleWord[i] = word.charAt(i);
			}
		}
		return puzzleWord;
	}

	public static boolean checkLegalTemplate(String word, boolean[] template) { // Q - 2
		if (word.length() != template.length) {
			return false;
		}
		boolean findT = false, findF = false;
		for( int k = 0; k < template.length; k++){
			if(template[k]){
				findT = true;
				if(findF){
					break;
				}
			} if(!template[k]){
				findF = true;
				if(findT){
					break;
				}
			}
		}
		if (findT == false || findF == false){
			return false;
		}
		for(int i = 0; i < template.length; i++){
			for(int j = i; j < template.length; j++) {
				if(word.charAt(i) == word.charAt(j)){
					if (template[i] != template[j])
						return false;
				}

			}
		}
		return true;
	}

	/*
	 * @pre: 0 < k < word.lenght(), word.length() <= 10
	 */
	public static int subset(int n, int k) {
		if (k == 0) {
			return 1;
		}
		if (n == k) {
			return 1;
		} else {
			return subset(n - 1, k - 1) + subset(n - 1, k);
		}
	}

	public static boolean[][] getAllLegalTemplates(String word, int k){  // Q - 3
		int size = subset(word.length(), k);
		int realSize = 0 ;
		boolean [][] allLegals = new boolean[size][word.length()];
		for (int l = 0; l < size; l++){
			allLegals[l] = new boolean[word.length()];
		}
		String binaryTemp = "";
		boolean[] tempBinary;
		for(int i = 1; i < Math.pow(2, word.length()); i++){



			binaryTemp = Integer.toBinaryString(i);
			for ( int m = 0 ; m < word.length(); m ++){
				if (binaryTemp.length() < word.length()){
					binaryTemp = "0" + binaryTemp;
				}
			}

			if(binaryTemp.length()- binaryTemp.replace("1","").length() == k){
				tempBinary = new boolean[word.length()];

				for (int j = 0 ; j < word.length(); j++){
					if(binaryTemp.charAt(j) == '0'){
						tempBinary[j] = false;
					} else{
						tempBinary[j] = true;
					}
				}
				if (checkLegalTemplate(word, tempBinary)){
					allLegals[realSize] = tempBinary;
					realSize ++;
				}
			}
		}
		if (realSize > 0){
			return Arrays.copyOf(allLegals, realSize);
		}
		else {
			boolean [][] temp = {};
			return temp;
		}
	}


	/*
	 * @pre: puzzle is a legal puzzle constructed from word, guess is in [a...z]
	 */
	public static int applyGuess(char guess, String word, char[] puzzle) { // Q - 4
		int counter = 0;
		for(int i = 0 ; i < puzzle.length; i ++){
			if (puzzle[i] == HIDDEN_CHAR){
				if (word.charAt(i) == guess){
					puzzle[i] = guess;
					counter ++;
				}
			}
		}
		return counter;
	}


	/*
	 * @pre: puzzle is a legal puzzle constructed from word
	 * @pre: puzzle contains at least one hidden character.
	 * @pre: there are at least 2 letters that don't appear in word, and the user didn't guess
	 */
	public static int tryGuess(char guess, String word, char[] puzzle) { // Q - 4
		int counter = 0;
		for(int i = 0 ; i < puzzle.length; i ++){
			if (puzzle[i] == HIDDEN_CHAR){
				if (word.charAt(i) == guess){
					counter ++;
				}
			}
		}
		return counter;
	}
	public static char[] getHint(String word, char[] puzzle, boolean[] already_guessed) { // Q - 5
		Random rand = new Random();
		int rand_int;
		boolean correct = false, inCorrect = false;
		char correctC = 'a' , incorrectC = 'a' , randChar;
		boolean [] currentStatus = new boolean[already_guessed.length];
		for (int i = 0; i < already_guessed.length; i++){
			currentStatus[i] = already_guessed[i];
		}
		for (int j = 0; j < puzzle.length; j++){
			if (puzzle[j]!=HIDDEN_CHAR) {
				currentStatus[(int) (puzzle[j]) - 97] = true;
			}
		}
		while ((correct == false) || (inCorrect == false)) {
			rand_int = rand.nextInt(26);
			randChar = (char) (rand_int + 97);
			if(currentStatus[rand_int] == false){

				if(tryGuess(randChar, word, puzzle) > 0 && correct == false){
					correctC = randChar;
					correct = true;
				} else if (tryGuess(randChar, word, puzzle) == 0 && inCorrect == false) {
					incorrectC = randChar;
					inCorrect = true;
				}

			}
		}
		char[] chars = new char[2];
		if ((int)(correctC) >= (int)(incorrectC)){
			chars[0] = incorrectC;
			chars[1] =correctC;
		} else{
			chars[1] = incorrectC;
			chars[0] =correctC;
		}

		//System.out.println(chars);
		// Replace this with your code
		return chars;
	}



	public static char[] mainTemplateSettings(String word, Scanner inputScanner) { // Q - 6
		Random rand = new Random();
		System.out.println("--- Settings stage ---");
		int input = -1;
		int numOfHidden = -1;
		String enterredPuzzle = "";
		while (true) {
			System.out.println("Choose a (1) random or (2) manual template:");
			if (inputScanner.hasNext()) {
				input = inputScanner.nextInt();
			}
			if (input == 1) {
				System.out.println("Enter number of hidden characters:");
				if (inputScanner.hasNext()) {
					numOfHidden = inputScanner.nextInt();
				}
				int len = getAllLegalTemplates(word, numOfHidden).length;
				if (len > 0) {
					int rand_int;
					rand_int = rand.nextInt(len);
					return createPuzzleFromTemplate(word, getAllLegalTemplates(word, numOfHidden)[rand_int]);

				} else {
					System.out.println("Cannot generate puzzle, try again.");
				}
			} else if (input == 2) {
				System.out.println("Enter your puzzle template:");
				if (inputScanner.hasNext()) {
					enterredPuzzle = inputScanner.next();
					String[] chars = enterredPuzzle.split(",");
					boolean[] correctPuzzle = new boolean[chars.length];
					for (int i = 0; i < chars.length; i++) {
						if (chars[i].charAt(0) == '_') {
							correctPuzzle[i] = true;
						} else if (chars[i].charAt(0) == 'X') {
							correctPuzzle[i] = false;

						}
					}
					if (checkLegalTemplate(word, correctPuzzle)) {
						return createPuzzleFromTemplate(word, correctPuzzle);

						}
					} else {
						System.out.println("Cannot generate puzzle, try again.");

					}
				}
			}
	}

	public static void mainGame(String word, char[] puzzle, Scanner inputScanner){ // Q - 7
		// Replace this with your code
		System.out.println("--- Game stage ---");
		boolean over = false;
		boolean [] allLetters = new boolean[26];
		for(int k=0; k < allLetters.length; k++){
			allLetters[k] = false;
		}
		int leftToPlay = 3;
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == HIDDEN_CHAR) {
				leftToPlay ++;
			} else{
				allLetters[(int) (puzzle[i]) - 97] = true;
			}
		}

		while (leftToPlay > 0) {
			String puzzleStr = "";
			for (int j = 0; j < puzzle.length; j++){
				puzzleStr = puzzleStr + puzzle[j];
			}
			System.out.println(puzzleStr);
			System.out.println("Enter Your guess:");
			String input = "";
			if (inputScanner.hasNext()) {
				input = inputScanner.next();
				char[] hints = new char[2];
				if (input.charAt(0) == 'H') {
					hints = getHint(word, puzzle, allLetters);
					System.out.println("Here's a hint for you: choose either " + hints[0] + " or " + hints[1]+".");

				} else {
					leftToPlay --;
					int isThere = applyGuess(input.charAt(0),word,puzzle);
					int isOver = 0;
					for (int l = 0; l < puzzle.length; l++){
						if (puzzle[l] != HIDDEN_CHAR){
							isOver++;
						}
					}
					if(isOver == puzzle.length){
						System.out.println("Congratulations! You solved the puzzle!");
						over = true;
						break;
					}
					if(isThere > 0){
						System.out.println("Correct Guess, " + leftToPlay+ " guesses left.");
					} else{
						System.out.println("Wrong Guess, " + leftToPlay + " guess left.");
					}
				}
			}



		}
		if (!over){
			System.out.println("Game over!");
		}
	}




/*************************************************************/
/********************* Don't change this ********************/
	/*************************************************************/

	public static void main(String[] args) throws Exception {
		if (args.length < 1){
			throw new Exception("You must specify one argument to this program");
		}
		String wordForPuzzle = args[0].toLowerCase();
		if (wordForPuzzle.length() > 10){
			throw new Exception("The word should not contain more than 10 characters");
		}
		Scanner inputScanner = new Scanner(System.in);
		char[] puzzle = mainTemplateSettings(wordForPuzzle, inputScanner);
		mainGame(wordForPuzzle, puzzle, inputScanner);
		inputScanner.close();
	}


	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterWord() {
		System.out.println("Enter word:");
	}

	public static void printSelectNumberOfHiddenChars(){
		System.out.println("Enter number of hidden characters:");
	}
	public static void printSelectTemplate() {
		System.out.println("Choose a (1) random or (2) manual template:");
	}

	public static void printWrongTemplateParameters() {
		System.out.println("Cannot generate puzzle, try again.");
	}

	public static void printEnterPuzzleTemplate() {
		System.out.println("Enter your puzzle template:");
	}


	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}


	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printHint(char[] hist){
		System.out.println(String.format("Here's a hint for you: choose either %s or %s.", hist[0] ,hist[1]));

	}
	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left.");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle!");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
