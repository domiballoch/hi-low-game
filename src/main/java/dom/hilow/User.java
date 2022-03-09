package dom.hilow;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
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

    public void gameSetup() throws Exception {
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

    private void userInput() throws Exception {
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

    protected void playAgain() throws Exception {
        System.out.println("Play again? y/n");
        play = buffer.readLine();

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
