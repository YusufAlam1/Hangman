public class Word {

    private String[] words;
    private char[][] guessedLetters;

    Word(String[] wrds) {
        words = wrds;
        guessedLetters = new char[words.length][];
        for (int i = 0; i < words.length; i++) {
            guessedLetters[i] = new char[words[i].length()];
            for (int j = 0; j < words[i].length(); j++) {
                guessedLetters[i][j] = '_';
            }
        }
    }

    Word() {
        words = new String[0];
        guessedLetters = new char[0][0];
    }

    public void setWords(String[] wrds) {
        words = wrds;
    }

    public String[] getWords() {
        return words;
    }

    public char[][] getGuessedLetters() {
        return guessedLetters;
    }

    public String toString() {
        return words + " ";
    }

    public void revealLetter(char guess, int wordIndex) {
        char normalizedGuess = Character.toUpperCase(guess);
        for (int i = 0; i < guessedLetters[wordIndex].length; i++) {
            if (Character.toUpperCase(words[wordIndex].charAt(i)) == normalizedGuess) {
                guessedLetters[wordIndex][i] = Character.toLowerCase(guess);
            }
        }
    }

    public boolean isGuessed(int wordIndex) {
        for (char letter : guessedLetters[wordIndex]) {
            if (letter == '_') {
                return false;
            }
        }
        return true;
    }
}