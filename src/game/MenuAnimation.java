package game;

import interfaces.Menu;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 * @author Noa Azoulay.
 */
public class MenuAnimation<T> implements Menu<T> {

    private List<MenuSelections<T>> selections;
    private T status;
    private String title;
    private KeyboardSensor keyboard;
    private AnimationRunner ar;
    private boolean stop;

    /**
     * Constructor for MenuAnimation.
     * @param  str  string for menu
     * @param  k    keyboard
     * @param  ar   animation runner
     */
    public MenuAnimation(String str, KeyboardSensor k, AnimationRunner ar) {
        this.title = str;
        this.keyboard = k;
        this.ar = ar;
        this.selections = new ArrayList<>();
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        try {
            d.drawImage(0, 0, ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                    "background.jpg")));
        }  catch (Exception e) {
            d.setColor(new Color(24, 24, 24));
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        }
        d.setColor(Color.WHITE);
        d.drawText(60, 150, this.title, 100);
        for (int i = 0; i < selections.size(); i++) {
            int[] x = {0, 520, 470, 0};
            int[] y = {200 + 70 * i, 200 + 70 * i, 260 + 70 * i, 260 + 70 * i};
            Polygon poly = new Polygon(x, y, 4);
            d.setColor(new Color(153, 153, 153));
            d.fillPolygon(poly);
            d.setColor(new Color(24, 24, 24));
            d.drawText(140, 250 + i * 70, this.selections.get(i).getMessage(), 50);
            d.setColor(Color.WHITE);
            d.drawText(140, 250 + i * 70, this.selections.get(i).getKey().toUpperCase(), 50);
        }
        this.status = null;
    }

    @Override
    public boolean shouldStop() {
        for (MenuSelections<T> selection : selections) {
            if (this.keyboard.isPressed(selection.getKey())) {
                this.stop = true;
            }
        }
        return this.stop;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new MenuSelections(key, message, returnVal));
    }

    @Override
    public T getStatus() {
        for (MenuSelections<T> selection : selections) {
            if (this.keyboard.isPressed(selection.getKey())) {
                if (selection.getSubmenu() != null) {
                    this.ar.run(selection.getSubmenu());
                    this.status = selection.getSubmenu().getStatus();
                    break;
                } else {
                    this.status = selection.getReturnVal();
                    break;
                }
            }
        }
        this.stop = false;
        return this.status;
    }

}
