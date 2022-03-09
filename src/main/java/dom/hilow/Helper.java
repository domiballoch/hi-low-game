package dom.hilow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    @Autowired
    private Outcomes outcomes;

    @Autowired
    private User user;

    protected void guessLogic() throws Exception {
        // guess logic
        if (user.getGuess() >= user.getRnList().get(0) && user.getRnList().get(1) >= user.getRnList().get(0)) {
            System.out.println("Well done! Lets continue...");
            outcomes.correct();
        }
        if (user.getGuess() <= user.getRnList().get(0) && user.getRnList().get(1) <= user.getRnList().get(0)) {
            System.out.println("Well done! Lets continue...");
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
