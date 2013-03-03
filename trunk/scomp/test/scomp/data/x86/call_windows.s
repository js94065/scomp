STRING_0:
  .ascii "42\12\0"

decaf_print:
  enterl $(4 * 0), $0

  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 1), %esp

  movl $0, %eax
  leavel
  retl

  leavel
  retl

decaf_main:
.globl _main
_main:
  enterl $(4 * 0), $0

  calll decaf_print
  addl $(4 * 0), %esp

  movl $0, %eax
  leavel
  retl

  movl $0, %eax
  leavel
  retl
