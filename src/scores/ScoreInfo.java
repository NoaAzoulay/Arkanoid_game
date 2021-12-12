package scores;

import java.io.Serializable;

/**
 * The type Score info.
 *
 * @author Noa Azoulay.
 */
public class ScoreInfo implements Comparable, Serializable {

    private String name;
    private int score;

    /**
     * Constructor for ScoreInfo.
     * @param  name   name of the player
     * @param  score  score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the player name.
     * @return  name  this.name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the player score.
     * @return  score  this.score
     */
    public int getScore() {
        return this.score;
    }

    @Override
    public int compareTo(Object comparison) {
        int compare = ((ScoreInfo) comparison).getScore();
        return compare - this.score;
    }

}
