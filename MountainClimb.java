package project4;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
 * This program reads a text file and inputs the information into a Binary
 * Search Tree. The program only recognizes Alphanumeric inputs and specific
 * words, eg. "food", "raft", "axe", etc.
 * This program is not iteractive and only outputs viable routes down from the
 * root node of the tree.
 *
 * 
 * @author Henry Yuan
 *
 */
public class MountainClimb {
    public static void main(String[] args) throws Exception {
        // determine if the command line argument exists
        if (args.length == 0) {
            System.err.println("Usage Error: the program expects file name as an argument. \n");
            System.exit(1);
        }

        // determine if the command line arguement contains a viable file.
        File file = new File(args[0]);

        // if file doesnt exist, exit the program
        if (!file.exists()) {
            System.err.println("Error: the file " + file.getAbsolutePath() + " cannot be opened. \n");
            System.exit(1);
        }
        Scanner fileReader = new Scanner(file);
        BSTMountain mountain = new BSTMountain();
        ArrayList<String> dataStrings = new ArrayList<String>();
        boolean newNode = false;
        String label = "";
        int food = 0;
        int raft = 0;
        int axe = 0;
        boolean fallenTree = false;
        boolean river = false;
        while (fileReader.hasNext()) { // iterate through the file and add it to the ArrayList
            String test = fileReader.next();
            dataStrings.add(test);
        }
        for (int i = 0; i < dataStrings.size(); i++) { // iterate through the ArrayList and create Hiker objects
            if (dataStrings.get(i).equals("food")) { // increment food if "food" is present
                food++;
            }
            if (dataStrings.get(i).equals("raft")) { // increment raft if "raft" is present
                raft++;
            }
            if (dataStrings.get(i).equals("axe")) { // increment axe if "axe" is present
                axe++;
            }
            if (i + 1 < dataStrings.size()) { // if "fallen tree" is present, make it true
                if (dataStrings.get(i).equals("fallen") && dataStrings.get(i + 1).equals("tree")) {
                    fallenTree = true;
                }
            }

            if (dataStrings.get(i).equals("river")) { // if "river" is present, make it true
                river = true;
            }
            if (dataStrings.get(i).length() == 1) { // parsing through the Alphanumeric inputs now.
                if (dataStrings.get(i) instanceof String) { // check if dataStrings.get(i) is a String
                    if (newNode == true) { // boolean for the first RestStop
                        RestStop rStop = new RestStop(label, food, raft, axe, fallenTree, river); // create a RestStop
                        mountain.add(rStop); // add it to the mountain
                        food = 0; // reset all of the values
                        raft = 0;
                        axe = 0;
                        fallenTree = false;
                        river = false;
                    }
                    label = dataStrings.get(i);
                    newNode = true;
                }
            }

            if (i + 1 == dataStrings.size()) { // create a RestStop on the last input.
                RestStop rStop = new RestStop(label, food, raft, axe, fallenTree, river);
                mountain.add(rStop);
            }

        }
        fileReader.close(); // close scanner
        List<String> out = new ArrayList<String>(); // output List
        out = mountain.viablePaths();

        for (int i = 0; i < out.size(); i++) { // format output
            for (int j = 0; j < out.get(i).length(); j++) {
                System.out.print(out.get(i).charAt(j) + " "); // output.
            }
            System.out.println();
        }
    }
}
