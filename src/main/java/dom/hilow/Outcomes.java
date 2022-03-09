package dom.hilow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Outcomes {

    @Autowired
    private User user;

    protected int win() throws Exception {
        // 10 correct guesses awards 1000
        System.out.println("Well done, you win the BRUCEY BONUS of $500");
        user.setBalance(user.getBalance() + 500);
        System.out.println("Your balance is now: " + user.getBalance());
        user.playAgain();
        return user.getBalance();
    }

    protected int correct() throws Exception {
        // correct guess awards 10
        user.setBalance(user.getBalance() + 10);
        System.out.println("You won $10, your balance is now: " + user.getBalance());
        return user.getBalance();
    }

    protected int match() {
        // correct guess awards 100
        System.out.println("It's also a match! A further $50 has been added to your balance");
        user.setBalance(user.getBalance() + 50);
        System.out.println("Your balance is now: " + user.getBalance());
        return user.getBalance();
    }

    protected int lose() throws Exception {
        // wrong guess deducts 100
        System.out.println("Game over! You lost $100");
        user.setBalance(user.getBalance() - 100);
        user.setGuessCount(0);

        if (user.getBalance() <= 0) {
            System.out.println("Out of funds. Game over!");
            System.exit(0);
        } else {
            user.playAgain();
        }

        return user.getBalance();
    }
}
