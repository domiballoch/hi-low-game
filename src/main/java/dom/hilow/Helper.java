package dom.hilow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    private static final String CONTINUE = "Well done! Lets continue...";

    @Autowired
    private Outcomes outcomes;

    @Autowired
    private User user;

    /**
     * Guess logic - win, lose
     */
    protected void guessLogic() {
        // guess logic
        if (user.getGuess() >= user.getRnList().get(0) && user.getRnList().get(1) >= user.getRnList().get(0)) {
            System.out.println(CONTINUE);
            outcomes.correct();
        }
        if (user.getGuess() <= user.getRnList().get(0) && user.getRnList().get(1) <= user.getRnList().get(0)) {
            System.out.println(CONTINUE);
            outcomes.correct();
        }
        if (user.getGuess() == user.getRnList().get(1)) {
            outcomes.match();
        }
        if (user.getGuess() >= user.getRnList().get(0) && user.getRnList().get(1) < user.getRnList().get(0)) {
            outcomes.lose();
        }
        if (user.getGuess() <= user.getRnList().get(0) && user.getRnList().get(1) > user.getRnList().get(0)) {
            outcomes.lose();
        }

        if (user.getGuessCount() == 5) {
            outcomes.win();
        }

        user.getRnList().remove(0);
        //To see list...
        //System.out.println(user.getRnList());
    }
}
