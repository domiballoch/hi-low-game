package dom.hilow;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Component
public class User {

    @Autowired
    private Helper helper;

    private int balance = 300;
    private int guess;
    private int guessCount = 0;
    private String play;
    private static Scanner input = new Scanner(System.in);
    private InputStreamReader inputStream = new InputStreamReader(System.in);
    private BufferedReader buffer = new BufferedReader(inputStream);
    protected ArrayList<Integer> rnList = new ArrayList<>();

    /**
     * Setup game with 5 random cards and welcome message
     */
    public void gameSetup() {
        // first create 5 random numbers in a list
        int count = 0;
        do {
            Random r = new Random();
            int x = r.nextInt(11 - 1) + 1;
            rnList.add(x);
            count++;
        } while (count < 6);

        final StringBuilder sb = new StringBuilder();
        sb.append("**************************************")
                .append("\n***WELCOME TO THE BRUCEY BONUS GAME***")
                .append("\n**************************************")
                .append("\nYour balance is: " + balance)
                .append("\nGuess higher or lower to win $$$")
                .append("\nWin $10 for each correct guess and $50 for an exact number match")
                .append("\nEach incorrect guess loses $100")
                .append("\nGuess 5 in a row for the BRUCEY BONUS or $500!!!").toString();
        System.out.println(sb);
        userInput();
    }

    /**
     * User input logic
     */
    private void userInput() {
        if (guessCount < 5) {
            // display starting number
            System.out.println("\nThe number is: " + rnList.get(0));

            // enter higher or lower
            System.out.print("Enter your guess, Higher or Lower? ");
            this.guess = input.nextInt();
            guessCount++;

            // display users guess
            if (guess > rnList.get(0)) {
                System.out.println("You guessed higher");
            }
            if (guess < rnList.get(0)) {
                System.out.println("You guessed lower");
            }

            // display next number
            System.out.println("The number is: " + rnList.get(1));

            helper.guessLogic();
            System.out.println("Number of guesses used: " + guessCount);

            userInput();
        } else {
            playAgain();
        }
    }

    /**
     * Play again logic yes/no
     */
    protected void playAgain() {
        System.out.println("Play again? y/n");
        try {
            play = buffer.readLine();
        } catch (IOException e) {
            log.error("Error reading buffered line {}", e.getMessage());
        } finally {
            if(buffer !=null || inputStream != null) {
                try {
                    buffer.close();
                    inputStream.close();
                } catch (IOException e) {
                    log.error("Error closing resources {}", e.getMessage());
                }
            }
        }

        // if no then close program
        if (play.equalsIgnoreCase("n")) {
            System.out.println("Thank you, goodbye");
            System.exit(0);
        } else {
            // if yes then play again
            if (play.equalsIgnoreCase("y")) {
                rnList.clear();
                gameSetup();
            }
        }
    }
}
