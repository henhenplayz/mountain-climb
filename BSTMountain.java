package project4;

import java.util.ArrayList;
import java.util.List;
/**
 * This file is implementing the BSTMountain object.
 * 
 * @author Henry Yuan
 *
 */
public class BSTMountain extends BST<RestStop> {
    /**
     * @return a List of viable paths
     */
    public List<String> viablePaths() {
        List<String> out = new ArrayList<String>();
        Hiker hiker = new Hiker(0, 0, 0);
        if (root != null) {
            pathsHelper(hiker, root, "", out);
        }
        for (int i = 0; i < out.size(); i++) {
            //System.out.println(out.get(i));
            if (out.get(i).length() < heightOfTree + 1) {
                //System.out.println(i);
                out.remove(i);
                i--;
                
            }
        }

        return out;
    }

    /**
     * @param hiker
     * @param current
     * @param path
     * @param out 
     */
    private void pathsHelper(Hiker hiker, Node current, String path, List<String> out) {

        hiker.addFood(current.data.getFood());
        hiker.addRaft(current.data.getRaft());
        hiker.addAxe(current.data.getAxe());
        if (current.height != 0) {
            hiker.useFood();
        }
        if (current.getData().fallenTree) {
            hiker.useAxe();
        }
        if (current.getData().river) {
            hiker.useRaft();
        }

        if (hiker.getFood() < 0 || hiker.getAxe() < 0 || hiker.getRaft() < 0) {
            hiker.food = -9000; // make sure the traversal does not continue
        } 
        else {
            out.add(path + current.data.label);
        }
        Hiker hikerLeft = new Hiker(hiker.food, hiker.raft, hiker.axe);
        Hiker hikerRight = new Hiker(hiker.food, hiker.raft, hiker.axe);
        if (current.left != null) {
            pathsHelper(hikerLeft, current.left, path + current.getData().label, out);

        }
        if (current.right != null) {
            pathsHelper(hikerRight, current.right, path + current.getData().label, out);
        }
        

    }

    /* (non-Javadoc)
     * @see project4.BST#toString()
     */
    public String toString() {
        String out = toString(root);

        out = out.substring(0, out.length() - 2);
        return "[" + out + "]";
    }

    /* (non-Javadoc)
     * @see project4.BST#toString(project4.BST.Node)
     */
    protected String toString(Node current) {
        String out = "";
        if (current == null) {
            return "";
        } else {
            out += current.getData().label.toString() + ", ";
            out += toString(current.getLeft());
            out += toString(current.getRight());
            return out;
        }
    }
}
