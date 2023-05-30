package pa3;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;  // Import this class to handle errors

/**
 * Skeleton match class
 */

public class Match {
    // Declare the variables.
    private Path[][] opt;
    int row_len, col_len, i, j, col_gap, row_gap, mis_match, min_cost;

    public Match(){}
    
// return the optimal match between the strings a and b
// return null if either string is null or if either string is length 0
    public Path match(String a, String b){
    // Corner case!
        if (a == null || b == null || a.length() == 0 || b.length() == 0)
            return null;

    // initialize the row and column length
        row_len = a.length() + 1;
        col_len = b.length() + 1;

    // initialize the 2D matrix.
        opt = new Path[row_len][col_len];

    // Set the 0th row and column to gap cost.
        for (i = 0; i < row_len; i++) {
            opt[i][0] = new Path();
            opt[i][0].setCost(i * 2);
        }

        for (j = 0; j < col_len; j++) {
            opt[0][j] = new Path();
            opt[0][j].setCost(j * 2);
        }


    // Set the x >= 2 rows and column to minimum cost.
        // For that row's
        for (i = 1; i < row_len; i++) {
            // column, and find the minimum cost for that index.
            for (j = 1; j < col_len; j++) {
                opt[i][j] = new Path();
                // If there is a match then it's same cost as previous.
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    opt[i][j].setCost(opt[i - 1][j - 1].getCost());
                    opt[i][j].setNext(opt[i - 1][j - 1]);
                } else {
                    // otherwise compare all the cost, (column vs row vs mismatch), and select the lowest cost.
                    col_gap = opt[i][j - 1].getCost() + 2;
                    row_gap = opt[i - 1][j].getCost() + 2;
                    mis_match = opt[i - 1][j - 1].getCost() + 1;
                    min_cost = Math.min(Math.min(col_gap, row_gap), mis_match);
                    if (min_cost == mis_match) {
                        opt[i][j].setCost(mis_match);
                        opt[i][j].setNext(opt[i-1][j-1]);
                    } else if (min_cost == col_gap) {
                        opt[i][j].setCost(col_gap);
                        opt[i][j].setNext(opt[i][j-1]);
                    } else {
                        opt[i][j].setCost(row_gap);
                        opt[i][j].setNext(opt[i-1][j]);
                    }
                }
                // Initialize the row and column for that index.
                opt[i][j].setRow(i);
                opt[i][j].setCol(j);
            }
        }
        // Return
        return opt[row_len - 1][col_len - 1];
    }

    // Test case to see the output
    public static void main(String[] args) {
        Match m = new Match();
        Path p1 = m.match("AACAGTTACC","TAAGGTCA");

        while (p1 != null) {
            System.out.print(p1.getCost() + " --> ");
            p1 = p1.getNext();
        }
    }
}

