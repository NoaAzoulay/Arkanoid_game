package game;

import geometry.Point;
import interfaces.LevelInformation;
import interfaces.Sprite;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level.
 *
 * @author Noa Azoulay.
 */
public class Level implements LevelInformation {

    private int name;

    @Override
    public int paddleSpeed() {
        return 700;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * Sets the level name.
     * @param  s  new level name
     */
    public void setLeveLName(int s) {
        this.name = s;
    }

    @Override
    public String levelName() {
        return "SPIDERS #" + this.name;
    }

    @Override
    public Sprite getBackground() {
        try {
            return new LevelBG(ImageIO.read(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("background.jpg")));
        } catch (Exception e) {
            return new LevelBG(Color.WHITE);
        }
    }

    @Override
    public List<Alien> aliens() {
        List<Alien> aliens = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                aliens.add(new Alien(new Point(1 + 50 * i, 30 + 50 * j)));
            }
        }
        return aliens;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.aliens().size();
    }

}
