import math
import stdio


# Entry point.
def main():
    # Assign ETA, RHO, T, and R to appropriate value.
    ETA = 9.135 * 10 ** -4
    RHO = 0.5 * 10 ** -6
    T = 297
    R = 8.31457
    METERPERPIXELS = 0.175 * 10 ** -6
    # Set n and var to 0, then calculate for var.
    n = 0
    var = 0
    while not stdio.isEmpty():
        displacements = stdio.readFloat()
        distance = displacements * METERPERPIXELS
        var += distance ** 2
        n += 1
    var = var / (2 * n)
    # Equation of Boltzmann's constant and avogadro's constant
    k = 6 * math.pi * var * ETA * RHO / T
    avogadro = R / k
    # Write two constants in standard output.
    stdio.writef('%e %e\n', k, avogadro)


if __name__ == '__main__':
    main()
