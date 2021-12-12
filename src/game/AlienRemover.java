package game;

import interfaces.HitListener;
import objects.Ball;
import objects.Paddle;

/**
 * The type Alien remover.
 * @author Noa Azoulay.
 */
public class AlienRemover implements HitListener {

    private Counter remainingBlocks;
    private GameLevel game;
    private AliensGroup aliens;

    /**
     * Constructor for listeners.AlienRemover.
     *
     * @param game          playing game
     * @param removedBlocks how many aliens were removed
     * @param a             aliens group
     */
    public AlienRemover(GameLevel game, Counter removedBlocks, AliensGroup a) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.aliens = a;
    }

    @Override
    public void hitEvent(Alien beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        if (this.aliens.getAliens().contains(beingHit)) {
            this.aliens.remove(beingHit);
            this.remainingBlocks.decrease(1);
        }
    }

    @Override
    public void hitEvent(Shield beingHit, Ball ball) {
    }

    @Override
    public void hitEvent(Paddle beingHit, Ball ball) {

    }

}
