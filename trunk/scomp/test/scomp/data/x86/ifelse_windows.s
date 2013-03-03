STRING_0:
  .ascii "%d < %d\12\0"

STRING_1:
  .ascii "%d >= %d\12\0"

decaf_compare:
  enterl $(4 * 0), $0

  pushl 8(%ebp)
  pushl 12(%ebp)
  popl %ecx
  popl %eax
  cmpl %ecx, %eax
  movl $0, %eax
  movl $1, %ecx
  cmovll %ecx, %eax
  pushl %eax

  popl %eax
  cmpl $0, %eax
  je else_0
  pushl 12(%ebp)
  pushl 8(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 3), %esp
  jmp end_if_0
else_0:
  pushl 12(%ebp)
  pushl 8(%ebp)
  movl $STRING_1, %eax
  pushl %eax
  calll _printf
  addl $(4 * 3), %esp
end_if_0:

  leavel
  retl

decaf_main:
.globl _main
_main:
  enterl $(4 * 0), $0

  pushl $2
  pushl $1
  calll decaf_compare
  addl $(4 * 2), %esp

  pushl $1
  pushl $2
  calll decaf_compare
  addl $(4 * 2), %esp

  movl $0, %eax

  leavel
  retl