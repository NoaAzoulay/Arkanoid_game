package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type Key press stoppable animation.
 *
 * @author Noa Azoulay.
 */
public class KeyPressStoppableAnimation implements Animation {


    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor for KeyPressSToppableAnimation.
     * @param  sensor     keyboard sensor
     * @param  key        key to stop
     * @param  animation  animation to start
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboard.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        } else if (this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
