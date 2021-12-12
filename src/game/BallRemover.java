package game;

import interfaces.HitListener;
import objects.Ball;
import objects.Paddle;

/**
 * The type Ball remover.
 *
 * @author Noa Azoulay. class game.BallRemover .
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private boolean lose;

    /**
     * Constructor for listeners.BallRemover.
     *
     * @param  g  game level
     */
    public BallRemover(GameLevel g) {
        this.game = g;
        this.lose = false;
    }

    @Override
    public void hitEvent(Paddle beingHit, Ball hitter) {
        this.lose = true;
    }

    @Override
    public void hitEvent(Alien beingHit, Ball hitter) {
        if (hitter.getColor().equals("e")) {
            return;
        }
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
    }

    @Override
    public void hitEvent(Shield beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
    }

    /**
     * Returns lose status.
     * @return  boolean  lose status
     */
    public boolean isLose() {
        return this.lose;
    }

    /**
     * Resets lose status.
     */
    public void reset() {
        this.lose = false;
    }


}
