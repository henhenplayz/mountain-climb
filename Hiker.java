package project4;
/**
 * This file is implementing Hiker Object.
 * 
 * 
 * @author Henry Yuan
 *
 */
public class Hiker {
    int food = 0;
    int raft = 0;
    int axe = 0;
    public Hiker(int food, int raft, int axe) {
        this.food = food;
        this.raft = raft;
        this.axe = axe;
    }
    /**
     * @return food
     */
    public int getFood() {
        return food;
    }

    /**
     * @param food set food
     */
    public void addFood(int food) {
        this.food += food;
    }

    /**
     * Subtract one food
     */
    public void useFood() {
        food--;
    }

    /**
     * @return raft
     */
    public int getRaft() {
        return raft;
    }

    /**
     * @param raft set raft
     */
    public void addRaft(int raft) {
        this.raft += raft;
    }

    /**
     *  Subtract one raft
     */
    public void useRaft() {
        raft--;
    }

    /**
     * @return axe
     */
    public int getAxe() {
        return axe;
    }

    /**
     * @param axe set axe
     */
    public void addAxe(int axe) {
        this.axe += axe;
    }

    /**
     * Subtract one axe
     */
    public void useAxe() {
        axe--;
    }

}
