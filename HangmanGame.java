package Culminating;

import java.io.*;
import java.util.*;

public class HangmanGame {
    public static void main(String[] args) {
        Scanner scanS = new Scanner(System.in);
        Scanner scanN = new Scanner(System.in);
        Scanner scanFile = new Scanner(System.in);
        PrintWriter pw;
        File nf = new File("words.txt");
        Word myWord = new Word();
        Name userName = new Name();
        int option;

        char[][] hangman = {
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', '-', '-', '+'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', '|'}, 
            {' ', ' ', ' ', ' ', 'T', 'H', 'E', ' ', ' ', 'O', ' ', ' ', '|'},
            {'H', 'A', 'N', 'G', 'M', 'A', 'N', ' ', '/', '|', '\\',' ', '|'},
            {' ', ' ', ' ', 'G', 'A', 'M', 'E', ' ', '/', ' ', '\\',' ', '|'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
            {'=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '=', '='}
        };

        displayHangman(hangman);

        System.out.println("Welcome to The Hangman Game before we start, what is your name?");
        System.out.print("First Name: ");
        String firstName = scanS.nextLine();
        userName.setFirstName(firstName);
        System.out.print("Last Name: ");
        String lastName = scanS.nextLine();
        userName.setLastName(lastName);

        System.out.println("Welcome " + userName.getFirstName() + " " + userName.lastNameFirstLetter() + ". ");
        System.out.println("\n*It is highly reccomended that prior to playing you have someone to first set the words (1), and them you must create the file (2), as it is possible that this data has not been stored yet.");

        do {
            System.out.println("\n1. Set the words");
            System.out.println("2. Create a file of the words");
            System.out.println("3. View file of words");
            System.out.println("4. Play the game");
            System.out.println("5. Exit");

            option = scanN.nextInt();

            switch(option) {
                case 1: {
                    System.out.println("How many words would you like to set?");
                    int numWords = scanN.nextInt();
                    System.out.println("\n*Note: If your word has a space ' ' in it, put a period '.' in place of it.\n");
                    String[] theWords = new String[numWords];
                    for (int i = 0; i < numWords; i++) {
                        System.out.print("Enter word " + (i + 1) + ": ");
                        theWords[i] = scanS.nextLine();
                    }
                    myWord.setWords(theWords);
                    break;
                }
                case 2: {
                    try {
                        pw = new PrintWriter(nf);
                        String[] fileWords = myWord.getWords();
                        for (int i = 0; i < fileWords.length; i++) {
                            pw.println(fileWords[i]);
                        }
                        pw.close();
                    } catch(IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    System.out.println("File created successfully");
                    break;
                }
                case 3: {
                    try {
                        scanFile = new Scanner(nf);
                        while(scanFile.hasNext()) {
                            String line = scanFile.nextLine();
                            System.out.println(line);
                        }
                        scanFile.close();
                    } catch(IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case 4: {
                    System.out.println("\nYou made it to the game! This is how you play.");
                    System.out.println("A word from one of the set words will be given in ALPHABETICAL ORDER.");
                    System.out.println("You have a selected number of lives (from the mode you choose) which determines your number of attempt(s)");
                    System.out.println("If you get a letter wrong, you lose a try, and a body part of the hangman will be displayed part by part.");
                    System.out.println("If you guess a correct letter of the word, your attempt(s) will not go down, and you continue playing");
                    System.out.println("The goal is to guess the word before the full body of the hangman is shown which indicates you have lost the game.");
                    System.out.println("\nSome thing to note are that the game is NOT cAsE SeNsItIvE, and if a guess has multiple words, type in '.' in place for a space '_'");
                    System.out.println("Best of luck!\n");

                    char playAgain;
                    do {
                        String[] words = readWordsFromFile(scanFile, nf);
                        bubbleSort(words);
                        Word currentWords = new Word(words);

                        for (int i = 0; i < words.length; i++) {
                            playHangman(currentWords, i, scanN, userName);
                        }

                        System.out.println("If you would like somebody else to play the game type Y, if you would like to return to the menu, press M, if you would like to end the game type in any other character.");
                        playAgain = scanS.nextLine().charAt(0);

                    } while(playAgain == 'Y' || playAgain == 'y');

                    if(playAgain == 'M' || playAgain == 'm') {
                        continue;
                    } else if(playAgain != 'Y' || playAgain != 'y') {
                        System.out.println("Thank you for playing... goodbye.");
                        System.exit(0);
                    }
                    break;
                }
                case 5: {
                    System.out.println("Thank you for playing... goodbye.");
                    System.exit(0);
                }
                default: {
                    System.out.println("Please choose a number from the provided menu");
                }
            }
        } while (option != 5);

        scanS.close();
    }

    public static String[] readWordsFromFile(Scanner scanFile, File nf) {
        String[] readWords = null;
        try {
            scanFile = new Scanner(nf);
            int numLines = 0;
            while (scanFile.hasNextLine()) {
                scanFile.nextLine();
                numLines++;
            }
            readWords = new String[numLines];
            scanFile.close();
            scanFile = new Scanner(nf);
            int i = 0;
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                readWords[i++] = line;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return readWords;
    }

    public static void bubbleSort(String[] readWords) {
        int rw = readWords.length;
        for (int i = 0; i < rw - 1; i++) {
            for (int j = 0; j < rw - i - 1; j++) {
                if (readWords[j].compareTo(readWords[j + 1]) > 0) {
                    String temp = readWords[j];
                    readWords[j] = readWords[j + 1];
                    readWords[j + 1] = temp;
                }
            }
        }
    }

    public static void playHangman(Word words, int wordIndex, Scanner scanN, Name userName) {
        int mode;
        int lives;
        char[][] hangman = {
            {' ', ' ', '+', '-', '-', '+', ' '},
            {' ', ' ', '|', ' ', ' ', '|', ' '}, 
            {' ', ' ', ' ', ' ', ' ', '|', ' '},
            {' ', ' ', ' ', ' ', ' ', '|', ' '},
            {' ', ' ', ' ', ' ', ' ', '|', ' '},
            {' ', ' ', ' ', ' ', ' ', '|', ' '},
            {'=', '=', '=', '=', '=', '=', ' '}
        };

        do {
            System.out.println("\nChoose which mode you would like to play the game in by choosing the corresponding number.\n1. Easy Mode: 12 lives\n2. Medium Mode: 6 lives\n3. Hard Mode: 3 lives");
            mode = scanN.nextInt();
            lives = 0;
            switch(mode) {
                case 1: { lives = 12; break; }
                case 2: { lives = 6; break; }
                case 3: { lives = 3; break; }
                default: { System.out.println("Invalid input"); break; }
            }
        } while(mode > 3 || mode < 1);

        int maxBodyParts = 6;
        int bodyPartsAdded = 0;
        Strin
