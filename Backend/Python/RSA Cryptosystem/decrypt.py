import rsa
import stdio
import sys


# Entry point.
def main():
    # Accept n(int) and d(int) as command line arguments.
    n = int(sys.argv[1])
    d = int(sys.argv[2])
    width = rsa.bitLength(n)
    message = stdio.readAll()

    # Using for-loop, decrypt the binary, and output it as character.
    for i in range(0, len(message)-1, width):
        s = message[i:i + width]
        y = rsa.bin2dec(s)
        decrypt = rsa.decrypt(y, n, d)
        stdio.write(chr(decrypt))


if __name__ == '__main__':
    main()
