STRING_0:
  .ascii "%d\12\0"

decaf_get42:
  enterl $(4 * 0), $0

  pushl $42
  popl %eax  

  leavel
  retl

  leavel
  retl

decaf_main:
.globl _main
_main:
  enterl $(4 * 0), $0

  calll decaf_get42
  addl $(4 * 0), %esp

  pushl %eax 

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 2), %esp

  movl $0, %eax

  leavel
  retl