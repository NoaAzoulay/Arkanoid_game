package game;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;
import objects.Ball;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * The type Aliens group.
 */
public class AliensGroup implements Sprite {

    private List<Alien> aliens;
    private GameLevel game;
    private long lastShot;
    private double speed, oldSpeed;
    private boolean lose;

    /**
     * Constructor for AliensGroup.
     *
     * @param g game level
     * @param s speed of group
     */
    public AliensGroup(GameLevel g, Double s) {
        this.aliens = new ArrayList<>();
        this.game = g;
        this.lastShot = 0;
        this.speed = s;
        this.lose = false;
        this.oldSpeed = this.speed;
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (Alien alien : this.aliens) {
            alien.drawOn(d);
        }
    }

    @Override
    public void timePassed(double dt) {
        this.shoot();
        this.move(dt);
    }

    /**
     * Returns a list of minimum aliens in the column.
     *
     * @param map map of aliens
     * @return List list of minimum aliens
     */
    public List<Alien> minList(HashMap<Double, List<Alien>> map) {
        Iterator it = map.entrySet().iterator();
        Alien min = null;
        ArrayList<Alien> minList = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            ArrayList<Alien> aliensList = ((ArrayList<Alien>) pair.getValue());
            for (Alien alien : aliensList) {
                min = aliensList.get(0);
                if (min.getY() <= alien.getY()) {
                    min = alien;
                }
            }
            minList.add(min);
            it.remove(); // avoids a ConcurrentModificationException
        }
        return minList;
    }

    /**
     * Returns a random minimum alien to shoot from.
     *
     * @param l list to sort
     * @return Alien minimal alien
     */
    public List<Alien> findMin(List<Alien> l) {
        HashMap<Double, List<Alien>> aliensMap = new HashMap<>();
        for (int i = 0; i < l.size(); i++) {
            double x = this.aliens.get(i).getX();
            if (aliensMap.containsKey(x)) {
                aliensMap.get(x).add(this.aliens.get(i));
            } else {
                aliensMap.put(x, new ArrayList<>());
                aliensMap.get(x).add(this.aliens.get(i));
            }
        }
        return this.minList(aliensMap);
    }

    /**
     * Makes random alien shoot.
     */
    public void shoot() {
        if (System.currentTimeMillis() - lastShot > 500) {
            List<Alien> mins = findMin(this.aliens);
            Random rand = new Random();
            Alien min = mins.get(rand.nextInt(mins.size()));
            Ball shot = this.game.enemyShot(new Point(min.getCollisionRectangle().getBottom().middle().getX(),
                    min.getCollisionRectangle().getBottom().middle().getY() + 1));
            shot.setVelocity(
                    Velocity.fromAngleAndSpeed(180, 200));
            shot.addToGame(this.game);
            this.lastShot = System.currentTimeMillis();
        }
    }

    /**
     * Adds alien.
     *
     * @param a alien to add
     */
    public void add(Alien a) {
        this.aliens.add(a);
    }

    /**
     * Removes alien.
     *
     * @param a alien to remove
     */
    public void remove(Alien a) {
        this.aliens.remove(a);
    }

    /**
     * Coordinates the group to move together.
     *
     * @param dt 1/fps
     */
    public void move(double dt) {
        for (int i = 0; i < this.aliens.size(); i++) {
            if (this.aliens.get(i).getX() <= 0
                    || this.aliens.get(i).getX() + 40 >= 800) {
                this.speed = -this.speed * 1.1;
                for (Alien alien : this.aliens) {
                    alien.setUpperLeft(alien.getX(), alien.getY() + 20);
                }
                break;
            }
        }
        for (Alien alien : this.aliens) {
            if (alien.getY() + 30 >= 450) {
                this.lose = true;
            }
        }
        for (Alien alien : this.aliens) {
            alien.move(this.speed * dt);
        }
    }

    /**
     * Returns lose status.
     *
     * @return boolean lose
     */
    public boolean isLose() {
        return this.lose;
    }

    /**
     * Resets aliens position and speed.
     */
    public void reset() {
        for (Alien alien : this.aliens) {
            alien.setUpperLeft(alien.getStartX(), alien.getStartY());
        }
        this.lose = false;
        this.speed = this.oldSpeed;
    }

    /**
     * Returns the list of aliens.
     *
     * @return List list of aliens.
     */
    public List<Alien> getAliens() {
        return this.aliens;
    }

}
