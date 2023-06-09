/******************************************************************
*
*   file:     cmds.c
*   author:   betty o'neil
*   date:     ?
*
*   semantic actions for commands called by tutor (cs341, mp1)
*
*   revisions:
*      9/90  eb   cleanup, convert function declarations to ansi
*      9/91  eb   changes so that this can be used for hw1
*      9/02  re   minor changes to quit command
*/
/* the Makefile arranges that #include <..> searches in the right
   places for these headers-- 200920*/

#include <stdio.h>
#include "slex.h"
/*===================================================================*
*
*   Command table for tutor program -- an array of structures of type
*   cmd -- for each command provide the token, the function to call when
*   that token is found, and the help message.
*
*   slex.h contains the typdef for struct cmd, and declares the
*   cmds array as extern to all the other parts of the program.
*   Code in slex.c parses user input command line and calls the
*   requested semantic action, passing a pointer to the cmd struct
*   and any arguments the user may have entered.
*
*===================================================================*/

PROTOTYPE int stop(Cmd *cp, char *arguments);
PROTOTYPE int mem_display(Cmd *cp, char *arguments);
PROTOTYPE int mem_set(Cmd *cp, char *arguments);
PROTOTYPE int help(Cmd *cp, char *arguments);

/* command table */

Cmd cmds[] = {{"md",  mem_display, "Memory display: MD <addr>"},
	            {"ms", mem_set, "Memory set: MS <addr> <value>"},
              {"h", help, "Help: H <command>"},
              {"s",   stop,        "Stop" },
              {NULL,  NULL,        NULL}
            };  /* null cmd to flag end of table */

char xyz = 6;  /* test global variable  */
char *pxyz = &xyz;  /* test pointer to xyz */
/*===================================================================*
*		command			routines
*
*   Each command routine is called with 2 args, the remaining
*   part of the line to parse and a pointer to the struct cmd for this
*   command. Each returns 0 for continue or 1 for all-done.
*
*===================================================================*/

int stop(Cmd *cp, char *arguments) 
{
  return 1;			/* all done flag */
}

/*===================================================================*
*
*   mem_display: display contents of 16 bytes in hex
*
*/

int mem_display(Cmd *cp, char *arguments)
{
  sscanf(arguments, "%s", arguments); // rm space in the front.
  printf("%s ", arguments);

  unsigned int addr;
  unsigned char *content;
  unsigned char *contentcpy;


  sscanf(arguments, "%08x", &addr);
  content = (unsigned char *) addr;
  contentcpy = (unsigned char *) addr;
  int i;

  for (i = 0; i < 16; i++) {
    printf("%02x ", *content++);
  }

  int j;
  for (j = 0; j < 16; j++) {
    if (32 < *contentcpy && *contentcpy < 127) {
      printf("%c ", *contentcpy);
    } else {
      printf("%c", '.');
    }
    contentcpy++;
  }
  printf("%s", "\n");
  return 0;			/* not done */
}


int mem_set(Cmd *cp, char *arguments)
{
  unsigned int addr;
  // unsigned char *ptr;
  unsigned int value; 

  sscanf(arguments, "%x %x", &addr, &value);
  // ptr = (unsigned char *)addr; 
  
  *(unsigned int *)addr = value;
  printf("%s\n", "OK");

  return 0;
} 

int help(Cmd *cp, char *arguments)
{
  int i = 0;
  Cmd *cp2;

  for (cp2 = &cmds[0]; cp2->cmdtoken == NULL; cp2++) {
  } 

  sscanf(arguments, "%s", arguments);

  if (strcmp(arguments, "") == 0 || strcmp(arguments, " ") == 0) {
    for (i = 0; i < 4; i++) {
      printf("%s\n", cp2->help);
      cp2++;
    }
  } else {
    for (i = 0; i < 4; i++) {
      if (strcmp(cp2->cmdtoken, arguments) == 0) {
        printf("%s\n", cp2->help);
        break;
      } else {
        cp2++;
      }
    }
  }

  return 0;
}
