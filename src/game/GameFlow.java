package game;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import scores.HighScoresAnimation;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.io.File;
import java.util.List;

/**
 * The class Game flow.
 *
 * @author Noa Azoulay.
 */
public class GameFlow {

    private AnimationRunner ar;
    private Counter score = new Counter(), lives = new Counter();
    private SpriteCollection sprites = new SpriteCollection();
    private File file;
    private HighScoresTable highScores;
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private double speed = 50;

    /**
     * Constructor for Gameflow.
     * @param  ar  animation runner
     * @param  g   keyboard
     * @param  hs  highscores file
     */
    public GameFlow(AnimationRunner ar, GUI g, File hs) {
        this.gui            = g;
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.ar             = ar;
        this.file           = hs;
        this.highScores     = HighScoresTable.loadFromFile(this.file);
        this.lives.increase(SpaceInvaders.LIVES);
    }

    /**
     * Runs the list of levels from input.
     * @param  levels  list of levels from input
     */
    public void runLevels(List<LevelInformation> levels) {
        int i = 1;
        Level spaceInvaders = new Level();
        while (true) {
            spaceInvaders.setLeveLName(i);
            GameLevel level = new GameLevel(spaceInvaders, this.keyboardSensor, this.ar,
                    this.lives, this.score, this.sprites, this.speed);
            level.initialize();
            level.run();
            this.speed *= 1.5;
            if (this.lives.getValue() == 0) {
                this.ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new EndScreen(this.score)));
                break;
            }
            i++;
        }
        if (this.highScores.getRank(this.score.getValue()) < this.highScores.size()) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Add Highscore", "What is your name?", "");
            this.highScores.add(new ScoreInfo(name, this.score.getValue()));
            try {
                this.highScores.save(this.file);
            } catch (Exception e) {
                System.err.println("Error while saving new highscore.");
                e.printStackTrace();
            }
        }
        this.ar.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.highScores)));
    }

}


