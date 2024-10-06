package project4;
/**
 * This file is reststop.
 * 
 * 
 * @author Henry Yuan
 *
 */
public class RestStop implements Comparable<RestStop> {
    String label;
    int food;
    int raft;
    int axe;
    boolean fallenTree;
    boolean river;


    public RestStop(String label, int food, int raft, int axe, boolean fallenTree, boolean river) {
        this.label = label;
        this.food = food;
        this.raft = raft;
        this.axe = axe;
        this.fallenTree = fallenTree;
        this.river = river;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label set label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return  food
     */
    public int getFood() {
        return food;
    }

    /**
     * @param food set food
     */
    public void setFood(int food) {
        this.food = food;
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
    public void setRaft(int raft) {
        this.raft = raft;
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
    public void setAxe(int axe) {
        this.axe = axe;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * Overrides compareto
     * uses get Label to compare two RestStops
     */
    @Override
    public int compareTo(RestStop o) {
        return this.getLabel().compareTo(o.getLabel());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     * Uses Labels of RestStops to make calculation
     */
    public boolean equals(Object o) {
        if (this.label.equals(o)) {
            return true;
        }
        return false;
    }
}
