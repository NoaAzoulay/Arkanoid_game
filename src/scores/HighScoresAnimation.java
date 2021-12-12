package scores;

import biuoop.DrawSurface;
import interfaces.Animation;

import javax.imageio.ImageIO;
import java.awt.Color;

/**
 * The type High scores animation.
 *
 * @author Noa Azoulay.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable hsTable;
    private boolean stop;

    /**
     * Constructor for HighScoresAnimation.
     * @param  scores  a HighScoreTable to display
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.hsTable = scores;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        try {
            d.drawImage(0, 0, ImageIO.read(ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("background.jpg")));
        } catch (Exception e) {
            d.setColor(new Color(225, 213, 1));
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        }
        d.setColor(Color.WHITE);
        d.drawText(60, 150, "HIGHSCORES", 100);
        d.drawText(240, 200, "#", 14);
        d.drawText(280, 200, "NAME", 14);
        d.drawText(510, 200, "SCORE", 14);
        for (int i = 0; i < this.hsTable.getHighScores().size(); i++) {
            d.setColor(new Color(148, 148, 148));
            d.drawLine(240, 210 + i * 30, 560, 210 + i * 30);
            d.setColor(Color.WHITE);
            d.drawText(240, 230 + i * 30, Integer.toString(i + 1), 14);
            d.drawText(280, 230 + i * 30, this.hsTable.getHighScores().get(i).getName(), 14);
            d.drawText(510, 230 + i * 30, Integer.toString(this.hsTable.getHighScores().get(i).getScore()),
                    14);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
