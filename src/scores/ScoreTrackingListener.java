package scores;

import game.Alien;
import game.Counter;
import game.Shield;
import interfaces.HitListener;
import objects.Ball;
import objects.Paddle;

/**
 * The type Score tracking listener.
 *
 * @author Noa Azoulay.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructor for ScoreTrackingListener.
     * @param  scoreCounter  counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Alien beingHit, Ball hitter) {
        this.currentScore.increase(100);
    }

    @Override
    public void hitEvent(Paddle beingHit, Ball hitter) {
    }

    @Override
    public void hitEvent(Shield beingHit, Ball hitter) {

    }

}

