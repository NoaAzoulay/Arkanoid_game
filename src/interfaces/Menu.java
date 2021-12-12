package interfaces;


/**
 * The interface Menu.
 *
 * @param <T> the type parameter
 * @author Noa Azoulay
 */
public interface Menu<T> extends Animation {


    /**
     * Add selection to the menu.
     *
     * @param key       key press to access selection
     * @param message   display message for menu option
     * @param returnVal need to check
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Returns status.
     *
     * @return status  status of the menu.
     */
    T getStatus();


}
