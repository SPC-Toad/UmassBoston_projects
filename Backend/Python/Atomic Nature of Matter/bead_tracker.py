import math
import stdio
import sys
from blob_finder import BlobFinder
from picture import Picture


# Entry point
def main():
    # Accept pixels (int), tau (float), and delta (float) as command-line arguments.
    p = int(sys.argv[1])
    tau = float(sys.argv[2])
    delta = float(sys.argv[3])
    frames = sys.argv[4:]
    # Find the closest distance between bead to bead. Then write the distances.
    bf = BlobFinder(Picture(frames[0]), tau)
    previousBeads = bf.getBeads(p)
    for i in range(1, len(frames)):
        bf = BlobFinder(Picture(frames[i]), tau)
        currentBeads = bf.getBeads(p)
        for currentBead in currentBeads:
            closest = math.inf
            for previousBead in previousBeads:
                distance = currentBead.distanceTo(previousBead)
                if distance <= delta and distance < closest:
                    closest = distance
            if closest != math.inf:
                stdio.writef('%.4f\n', closest)
        stdio.writeln()
        # Set prevBeads to currBeads
        previousBeads = currentBeads


if __name__ == '__main__':
    main()
