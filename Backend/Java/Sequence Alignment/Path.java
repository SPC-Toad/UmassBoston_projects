package pa3;
import java.util.*;
import java.io.*;

/**
 * Skeleton Class for a Path in the Dynamic Programming sequence alignment algorithm
 */
public class Path {
    private int row, col;          // the row and column this node represents
    private int cost;              // the matching cost from this point on
    private Path next;             // the next node in the optimal path

    public Path()
    {
        // Default next to null
        next = null;
    }
    
    public int getCost() {return cost;}
    public void setCost(int c) {cost = c;}

    public int getRow() {return row;}
    public void setRow(int r) {row = r;}
   
    public int getCol() {return col;}
    public void setCol(int c) {col = c;}

    public Path getNext() {return next;}
    public void setNext(Path p) {next = p;}

  
}
