package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.Animation;

/**
 * The type Animation runner.
 *
 * @author Noa Azoulay.
 */
public class AnimationRunner {


    private GUI gui;
    private int framesPerSecond;

    /**
     * Constructor for Animation.AnimationRunner.
     * @param  gui  the game gui
     */
    public AnimationRunner(GUI gui) {
        this.gui                = gui;
        this.framesPerSecond    = 60;
    }

    /**
     * Play the animation.
     * @param  animation  animation to play
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, (double) 1 / this.framesPerSecond);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}
