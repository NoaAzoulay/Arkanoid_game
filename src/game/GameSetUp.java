package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.LevelInformation;
import interfaces.Menu;
import interfaces.Task;
import scores.HighScoresAnimation;
import scores.HighScoresTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game set up.
 *
 * @author Noa Azoulay.
 */
public class GameSetUp {
    /**
     * Sets up.
     *
     * @param args the args
     * @param gui  the gui
     * @param ks   the ks
     * @param ar   the ar
     * @return the up
     */
    public static Menu<Task<Void>> setUp(String[] args, GUI gui, KeyboardSensor ks, AnimationRunner ar) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("SPIDERS", ks, ar);
        File highScoresFile = new File("highscores");

        /*  - - - - - - - START - - - - - - -  */
        Task<Void> start = new Task<Void>() {
            @Override
            public Void run() {
                List<LevelInformation> level = new ArrayList<>();
                level.add(new Level());
                GameFlow gameFlow = new GameFlow(ar, gui, highScoresFile);
                gameFlow.runLevels(level);
                return null;
            }
        };

        /*  - - - - - - - HIGHSCORES TASK - - - - - - -  */
        Task<Void> highscores = new Task<Void>() {
            @Override
            public Void run() {
                HighScoresTable hst = null;
                try {
                    if (highScoresFile.exists()) {
                        hst = HighScoresTable.loadFromFile(highScoresFile);
                    } else {
                        hst = new HighScoresTable(SpaceInvaders.TABLE_SIZE);
                        hst.save(highScoresFile);
                    }
                } catch (Exception e) {
                    hst = new HighScoresTable(SpaceInvaders.TABLE_SIZE);
                }
                final HighScoresTable hiscores = hst;
                ar.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new HighScoresAnimation(hiscores)));
                return null;
            }
        };

        /*  - - - - - - - QUIT GAME - - - - - - -  */
        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                gui.close();
                System.exit(0);
                return null;
            }
        };

        /* MENU SELECTIONS */
        menu.addSelection("s", "START", start);
        menu.addSelection("h", "HIGHSCORES", highscores);
        menu.addSelection("q", "QUIT", quit);
        return menu;
    }

}
