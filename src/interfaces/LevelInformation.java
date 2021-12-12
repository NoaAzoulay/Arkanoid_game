package interfaces;

import game.Alien;

import java.util.List;

/**
 * The interface Level information.
 * @author Noa Azoulay
 */
public interface LevelInformation {

    /**
     * Speed of the paddle for this level.
     *
     * @return int speed of the paddle
     */
    int paddleSpeed();

    /**
     * Width of the paddle for this level.
     *
     * @return int width of the paddle
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return String name of the level
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return sprites.Sprite background of level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return List list of this level's aliens
     */
    List<Alien> aliens();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= aliens.size();
     *
     * @return int number of destroyable aliens
     */
    int numberOfBlocksToRemove();

}
