import rsa
import stdio
import sys


# Entry point.
def main():
    # Accept n(int) and e(int) as command-line argument.
    n = int(sys.argv[1])
    e = int(sys.argv[2])
    width = rsa.bitLength(n)
    message = stdio.readAll()
    # Using for-loop, encrypt characters to binary numbers.
    for c in message:
        x = ord(c)
        encrypt = rsa.encrypt(x, n, e)
        stdio.write(rsa.dec2bin(encrypt, width))
    stdio.writeln()


if __name__ == '__main__':
    main()
