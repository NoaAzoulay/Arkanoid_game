package game;

import interfaces.Menu;


/**
 * The type Menu selections.
 *
 * @param <T> the type parameter
 * @author Noa Azoulay.
 */
public class MenuSelections<T> {

    private String key;
    private String message;
    private T returnVal;
    private Menu<T> submenu;

    /**
     * Constructor with T val.
     * @param  key        key
     * @param  message    message
     * @param  returnVal  some value
     */
    public MenuSelections(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * Constructor with menu<T>.
     * @param  key      key
     * @param  message  message
     * @param  subMenu  submenu
     */
    public MenuSelections(String key, String message, Menu<T> subMenu) {
        this.key = key;
        this.message = message;
        this.submenu = subMenu;
    }

    /**
     * Returns this.key.
     * @return  this.key  key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Returns this.message.
     * @return  this.message  message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns this.submenu.
     * @return  this.submenu  submenu
     */
    public Menu<T> getSubmenu() {
        return this.submenu;
    }

    /**
     * Returns this.returnVal.
     * @return  this.returnVal  returnVal
     */
    public T getReturnVal() {
        return this.returnVal;
    }

}
