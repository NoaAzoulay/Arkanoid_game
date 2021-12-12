package interfaces;

import game.Alien;
import game.Shield;
import objects.Ball;
import objects.Paddle;

/**
 * @author Noa Azoulay.
 */
public interface HitListener {

    /**
     * Hit Event by Alien.
     * @param  hitted  hitted alien
     * @param  ball    hitting ball
     */
    void hitEvent(Alien hitted, Ball ball);

    /**
     * Hit Event by Shield.
     * @param  hitted  hitted shield
     * @param  ball    hitting ball
     */
    void hitEvent(Shield hitted, Ball ball);
    /**
     * Hit Event by Paddle.
     * @param  hitted  hitted paddle
     * @param  ball    hitting ball
     */
    void hitEvent(Paddle hitted, Ball ball);

}
