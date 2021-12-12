package game;

import interfaces.HitListener;
import objects.Ball;
import objects.Paddle;

/**
 * The type Shield remover.
 * @author Noa Azoulay.
 */
public class ShieldRemover implements HitListener {

    private GameLevel game;

    /**
     * Constructor for ShieldRemover.
     *
     * @param game played game
     */
    public ShieldRemover(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Alien hitted, Ball ball) {

    }

    @Override
    public void hitEvent(Shield beingHit, Ball ball) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
    }

    @Override
    public void hitEvent(Paddle hitted, Ball ball) {

    }
}
