import stdarray
import stdio
import stdrandom
import sys


# Generates and returns the public/private keys as a tuple (n, e, d). Prime numbers p and q
# needed to generate the keys are picked from the interval [lo, hi).
def keygen(lo, hi):
    primes = _primes(lo, hi)
    p, q = _sample(primes, 2)
    n = p * q
    m = (p - 1) * (q - 1)
    primes1 = _primes(2, m)
    e = _choice(primes1)
    while m % e == 0:
        e = _choice(primes1)

    d = 0
    for i in range(1, m):
        if (e*i) % m == 1:
            d = i
            break

    tuple1 = (n, e, d)
    return tuple1


# Encrypts x (int) using the public key (n, e) and returns the encrypted value.
def encrypt(x, n, e):
    return (x ** e) % n


# Decrypts y (int) using the private key (n, d) and returns the decrypted value.
def decrypt(y, n, d):
    return y ** d % n


# Returns the least number of bits needed to represent n.
def bitLength(n):
    return len(bin(n)) - 2


# Returns the binary representation of n expressed in decimal, having the given width, and padded
# with leading zeros.
def dec2bin(n, width):
    return format(n, '0%db' % width)


# Returns the decimal representation of n expressed in binary.
def bin2dec(n):
    return int(n, 2)


# Returns a list of primes from the interval [lo, hi).
def _primes(lo, hi):
    prime = []
    for p in range(lo, hi):
        if p < 2:
            continue
        j = 2
        while j <= p / j:
            if p % j == 0:
                break
            j += 1
        if j > p / j:
            prime += [p]
    return prime


# Returns a list containing a random sample (without replacement) of k items from the list a.
def _sample(a, k):
    b = a[:]
    for i in range(k):
        r = stdrandom.uniformInt(i, len(a))
        temp = b[r]
        b[r] = b[i]
        b[i] = temp
    return b[:k]


# Returns a random item from the list a.
def _choice(a):
    r = stdrandom.uniformInt(0, len(a))
    return a[r]


# Unit tests the library [DO NOT EDIT].
def _main():
    x = ord(sys.argv[1])
    n, e, d = keygen(25, 100)
    encrypted = encrypt(x, n, e)
    stdio.writef('encrypt(%c) = %d\n', x, encrypted)
    decrypted = decrypt(encrypted, n, d)
    stdio.writef('decrypt(%d) = %c\n', encrypted, decrypted)
    width = bitLength(x)
    stdio.writef('bitLength(%d) = %d\n', x, width)
    xBinary = dec2bin(x, width)
    stdio.writef('dec2bin(%d) = %s\n', x, xBinary)
    stdio.writef('bin2dec(%s) = %d\n', xBinary, bin2dec(xBinary))


if __name__ == '__main__':
    _main()
