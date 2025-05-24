package hangman;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Game {

    public static int guessed;

    public static int findIndex(char character) {
        // to lower case
        int index = character % 26;
        return index;
    }

    // continue game function
    public static boolean continueGame(int counter, int guessed, int man) {
        boolean cont = true;
        if (guessed >= counter) {
            System.out.println("Guessed is " + guessed + " and counter is " + counter);
            cont = false;
            System.out.println("You guessed the word!");
        }
        if (man == 6) {
            cont = false;
            System.out.println("You ran out of guesses!");
        }
        return cont;
    }

    public static boolean partOfWord(char[] letters, char input) {
        boolean present = false;
        for (int i = 0; i < letters.length; i++) { // must account for returning index and if same letter appears
                                                   // multiple times - return multiple indices
            if (letters[i] == input) {
                System.out.println("The letter " + input + " is present");
                present = true;
            }
        }
        return present;
    }

    public static boolean validInput(String input, boolean[] check) {
        if (input.isEmpty()) {
            System.out.println("You must enter a letter!");
            return false;
        }
        if (input.length() > 1) {
            System.out.println("You can only enter one letter at a time!");
            return false;
        }
        char character = input.charAt(0);
        if (Character.isAlphabetic(character) == false) {
            System.out.println("You need to enter a letter!");
            return false;
        }

        int index = findIndex(character);
        if (check[index] == true) {
            System.out.println("You already guessed that letter!");
            return false;
        }
        return true;
    }

    public static char[] updateReveal(char[] reveal, char input, char[] letters) {
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == input) {
                reveal[i] = input;
                guessed += 1;
                // System.out.println("reveal was updated with " + input);
                // System.out.println("guessed is " + guessed);
            }
        }
        return reveal;
    }

    public static void printReveal(char[] reveal) {
        for (int i = 0; i < reveal.length; i++) {
            System.out.print(reveal[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // set of possible words

        String[] words = { "owl", "headphones", "blueberry", "jewel", "pillow", "laptop" };

        // Select a word for the game

        Random random = new Random();
        int index = random.nextInt(0, words.length);
        String word = words[index];
        int counter = word.length();
        guessed = 0;
        int man = 0;

        // set up boolean array to keep track of guessed letters
        boolean[] check = new boolean[26];

        // set up array for word
        char[] letters = word.toCharArray(); // size needs to be length of string

        // set up reveal array
        char[] reveal = new char[word.length()];
        Arrays.fill(reveal, '_');

        // set up scanner
        Scanner scanner = new Scanner(System.in);

        // set up hangman
        Hangman hangman = new Hangman();

        System.out.println("Welcome to Hangman!");
        // TO-DO: add instructions

        printReveal(reveal);

        while (continueGame(counter, guessed, man) == true) {

            // read in a letter from the user
            System.out.println("Guess a letter!");
            String checkInput = scanner.nextLine();
            // check if valid input
            while (validInput(checkInput, check) == false) {
                System.out.println("Guess a letter!");
                checkInput = scanner.nextLine();
            }
            // if valid, check if letter was already guessed
            char input = checkInput.charAt(0);
            // if new guess, check if letter is part of word
            boolean included = partOfWord(letters, input);
            // if yes, reveal the letter
            if (included == true) {
                reveal = updateReveal(reveal, input, letters);
                // if no, add a part to the hang man
            } else {
                man += 1;
                hangman.addLimb();
                // add to hangman
            }
            int checkIndex = findIndex(input);
            check[checkIndex] = true;
            printReveal(reveal);

            // if no, add a part to the hang man

            // remove letter from list

        }

        scanner.close();

    }
}
