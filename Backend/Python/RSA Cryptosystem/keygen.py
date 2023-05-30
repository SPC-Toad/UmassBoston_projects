import rsa
import stdio
import sys


# Entry point.
def main():
    # Accept lo(int) and hi(int) as command-line arguments.
    lo = int(sys.argv[1])
    hi = int(sys.argv[2])
    keys = rsa.keygen(lo, hi)
    # Write each value with space between them.
    stdio.writef('%d %d %d\n', keys[0], keys[1], keys[2])


if __name__ == '__main__':
    main()
