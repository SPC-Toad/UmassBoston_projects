package pa3;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;

/**
 * Class to Implement MTF
 */
public class MoveToFront {
    public MoveToFront() {
    }
    // code here
    public static void decode(String f) {
        // Declare variable
        int input;
        int output;
        LinkedList<Integer> list = new LinkedList<>();

        // List of ascii code
        for (int i = 0; i < 256; i++) {
            list.add(i);
        }
        // Store the file to a variable.
        BinaryIn bin = new BinaryIn(f);

        // Read the character until EOF character.
        while (!bin.isEmpty()) {
            // Read the Character.
            input = bin.readByte();
            // Get the ascii value from the list at the index(input) number.
            output = list.get(input);
            // print
            StdOut.print((char) output);
            // remove the node that has the item == character
            list.remove(input);
            // Add it to the front of the list.
            list.addFirst(output);
        }
    }

    public static void encode(String f) {
        // Declare the variables
        int input, index;
        LinkedList<Integer> list = new LinkedList<>();

        // List of ascii code
        for (int i = 0; i < 256; i++) {
            list.add(i);
        }
        // Store the file to a variable.
        BinaryIn bin = new BinaryIn(f);

        // Read the character until EOF character.
        while (!bin.isEmpty()) {
            // Read the Character.
            input = bin.readChar();
            // Find the index of the node where its item == character.
            index = list.indexOf(input);
            // print
            StdOut.print((char) index);

            // remove that index
            list.remove(index);
            // Add it to first with item == input.
            list.addFirst(input);
        }
    }

    // Test case to ensure the code works properly.
    public static void main(String[] args) {
        MoveToFront mtf = new MoveToFront();

        mtf.decode("./test_cases/abra_dec.txt");
        StdOut.println();
        mtf.encode("./test_cases/abra.txt");

    }


}
