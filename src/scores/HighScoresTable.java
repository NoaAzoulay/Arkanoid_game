package scores;


import game.SpaceInvaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * The type High scores table.
 *
 * @author Noa Azoulay.
 */
public class HighScoresTable implements Serializable {

    private ArrayList<ScoreInfo> highScores;
    private int tableSize;

    /**
     * Create an empty high-highScores table with the specified tableSize.
     * The tableSize means that the table holds up to tableSize top highScores.
     * @param  tableSize  tableSize of table
     */
    public HighScoresTable(int tableSize) {
        this.tableSize = tableSize;
        this.highScores = new ArrayList<>();
    }

    /**
     * Add a high-score.
     * @param  score  score to add
     */
    public void add(ScoreInfo score) {
        this.highScores.add(score);
        Collections.sort(this.highScores);
        if (this.highScores.size() > this.tableSize) {
            this.highScores.remove(this.tableSize);
        }
    }

    /**
     * Return table tableSize.
     * @return  tableSize  tableSize of table
     */
    public int size() {
        return this.tableSize;
    }

    /**
     * Return the current high highScores. The list is sorted such that the highest highScores come first.
     * @return  highscores  list of highscores
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `tableSize` means the score will be lowest.
     * Rank > `tableSize` means the score is too low and will not be added to the list.
     * @param   score  score for rank
     * @return  rank   rank of the score
     */
    public int getRank(int score) {
        for (int i = 0; i < this.highScores.size(); i++) {
            if (score >= this.highScores.get(i).getScore()) {
                return i + 1;
            }
        }
        if (this.highScores.size() >= this.size()) {
            return this.highScores.size();
        }
        return this.tableSize - 1;
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param   filename     file
     * @throws  IOException  io exception
     */
    public void load(File filename) throws IOException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            this.highScores = (ArrayList<ScoreInfo>) ois.readObject();
        } catch (Exception e) {
            System.err.println("Error while loading file.");
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing the file after loading.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Save table data to the specified file.
     * @param   filename     file
     * @throws  IOException  io exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(this.highScores);
        } catch (IOException e) {
            System.err.println("Something went wrong while writing highscores file.");
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing the file after saving.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param   filename         name of file to load
     * @return  HighScoresTable  loaded highscores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable hst = new HighScoresTable(SpaceInvaders.TABLE_SIZE);
        try {
            if (!filename.exists()) {
                hst = new HighScoresTable(SpaceInvaders.TABLE_SIZE);
            } else {
                hst.load(filename);
            }
        } catch (Exception h) {
            hst = new HighScoresTable(SpaceInvaders.TABLE_SIZE);
            System.err.println("Problem loading highscores!");
            h.printStackTrace();
        }
        return hst;
    }

}