package game;

/**
 * The type game.Counter.
 *
 * @author Noa Azoulay .
 */
public class Counter {

    private int count;

    /**
     * Constructor for Counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Add number to current count.
     * @param  number  number to add.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Subtract number from current count.
     * @param  number  number to subtract
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Get current count.
     * @return  int  current count of count
     */
    public int getValue() {
        return this.count;
    }

}
