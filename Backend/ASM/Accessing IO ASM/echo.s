.text
.globl echo
echo:
    movl 4(%esp), %edx     # get the com port address
checkstatus:
    addl $5, %edx          # point to line status port
readdata:
    inb (%dx), %al         # Read from COM port
    andb $1, %al           # Checks if the Data Ready is 1 (If the user entered value).
    jz readdata            # Loop to keep checking for updated Data ready value.

    subl $5, %edx          # Since Data Ready is 1, now move back to base address of the port.
    inb (%dx), %al         # Read byte of the input.
    cmpb 8(%esp), %al      # Check if the input was escape value.
    jz end                 # If they are equal, then we end the loop.

    outb %al, (%dx)        # Since input is NOT escape key, we can output back on screen.
    jmp checkstatus        # Send it back to loop 0.
end:
    ret
    .end