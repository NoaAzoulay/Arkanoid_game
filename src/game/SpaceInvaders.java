package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Menu;
import interfaces.Task;

/**
 * The type Space invaders.
 * @author Noa Azoulay.
 */
public class SpaceInvaders {

    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;
    /**
     * The constant TABLE_SIZE.
     */
    public static final int TABLE_SIZE = 10;
    /**
     * The constant LIVES.
     */
    public static final int LIVES = 3;

    /**
     * Main method when running the game.
     *
     * @param args list of a level set
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Space Invaders OOP assignment 7", WIDTH, HEIGHT);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui);
        Menu<Task<Void>> menu = GameSetUp.setUp(args, gui, ks, ar);

        while (true) {
            ar.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

}
