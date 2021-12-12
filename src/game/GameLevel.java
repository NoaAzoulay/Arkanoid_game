package game;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import objects.Ball;
import objects.Paddle;
import scores.ScoreIndicator;
import scores.ScoreTrackingListener;

import java.util.List;
import java.util.ArrayList;


/**
 * class Game.
 *
 * @author Noa Azoulay.
 */
public class GameLevel implements Animation {

    private double speed;
    private AnimationRunner runner;
    private boolean stop;
    private SpriteCollection sprites;
    private GameEnvironment environment = new GameEnvironment();
    private KeyboardSensor keyboard;
    private Counter blockCounter = new Counter(), score, lives;
    private LevelInformation level;
    private List<Ball> shots = new ArrayList<>();
    private Paddle paddle;
    private AliensGroup aliens;
    private BallRemover ballRemover = new BallRemover(this);

    /**
     * Constructor for GameLevel.
     * @param  level    level to play
     * @param  ks       ke
     * @param  ar       animation runner
     * @param  lives    lives to update
     * @param  score    score to update
     * @param  sprites  sprites collection
     * @param  s        speed of aliens
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner ar,
                     Counter lives, Counter score, SpriteCollection sprites, double s) {
        this.sprites        = sprites;
        this.keyboard       = ks;
        this.score          = score;
        this.lives          = lives;
        this.runner         = ar;
        this.level          = level;
        this.speed          = s;
        this.aliens         = new AliensGroup(this, this.speed);
    }

    /**
     * Adds given collidable to the game.
     * @param  c  parameter collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Removes given collidable from the game.
     * @param  c  parameter collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Adds given sprite to the game.
     * @param  s  parameter sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes given sprite from the game.
     * @param  s  parameter sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        // LISTENERS
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        // BACKGROUND
        addSprite(this.level.getBackground());

        //SHIELDS
        List<Shield> shields = new ArrayList<>();
        for (int i = 0, k = -1; i < 3; i++) {
            for (int j = 0; j < 90; j++) {
                if (j % 30 == 0) {
                    k++;
                }
                shields.add(new Shield(new Point(110 + 4 * j + k * 110, 450 + 4 * i)));
            }
            k = -1;
        }
        for (Shield shield : shields) {
            shield.addToGame(this);
            shield.addHitListener(this.ballRemover);
        }

        // BLOCKS
        List<Alien> aliensList = this.level.aliens();
        for (Alien alien : aliensList) {
            alien.addToGame(this);
            alien.addHitListener(scoreListener);
            alien.addHitListener(this.ballRemover);
            this.aliens.add(alien);
        }
        this.blockCounter.increase(this.level.numberOfBlocksToRemove());

        // INDICATORS
        GameLevelIndicator levelIndicator = new GameLevelIndicator(this.level.levelName());
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        this.sprites.addSprite(levelIndicator);
        this.sprites.addSprite(scoreIndicator);
        this.sprites.addSprite(livesIndicator);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        while (this.lives.getValue() > 0 && this.blockCounter.getValue() > 0) {
            playOneTurn();
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.blockCounter.getValue() == 0 || this.aliens.isLose() || this.ballRemover.isLose()) {
            if (this.aliens.isLose() || this.ballRemover.isLose()) {
                this.lives.decrease(1);
            }
            this.stop = true;
            this.aliens.reset();
            this.ballRemover.reset();
            for (Ball shot : this.shots) {
                shot.removeFromGame(this);
            }
            this.shots.clear();
        } else {
            this.stop = false;
        }
        return this.stop;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.paddle.shoot(this);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.aliens.timePassed(dt);
    }

    /**
     * Plays the game.
     */
    public void playOneTurn() {
        // CREATE PADDLE
        this.paddle = new Paddle((SpaceInvaders.WIDTH - this.level.paddleWidth()) / 2,
                this.level.paddleWidth(), this.level.paddleSpeed(), this.keyboard);
        this.paddle.addHitListener(this.ballRemover);
        this.paddle.addToGame(this);

        // COUNTDOWN
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));

        // START GAME
        this.stop = false;
        this.runner.run(this);

        // REMOVE PADDLE
        this.sprites.removeSprite(this.paddle);
        this.removeCollidable(this.paddle);
    }

    /**
     * Adds player shot to the game.
     * @param   p     starting point of shot
     * @return  shot  new ball as shot
     */
    public Ball playerShot(Point p) {
        Ball shot = new Ball(p, "p", this.environment);
        this.shots.add(shot);
        shot.addHitListener(new AlienRemover(this, this.blockCounter, this.aliens));
        shot.addHitListener(new ShieldRemover(this));
        return shot;
    }

    /**
     * Adds enemy shot to the game.
     * @param   p     starting point of shot
     * @return  shot  new ball as shot
     */
    public Ball enemyShot(Point p) {
        Ball shot = new Ball(p, "e", this.environment);
        this.shots.add(shot);
        shot.addHitListener(new ShieldRemover(this));
        return shot;
    }


}
