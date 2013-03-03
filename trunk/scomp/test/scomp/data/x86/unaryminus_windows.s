STRING_0:
  .ascii "-%d = %d\12\0"

decaf_main:
.globl _main
_main:
  enterl $(4 * 1), $0

  movl $0, -4(%ebp)
  pushl $2
  popl -4(%ebp)

  pushl -4(%ebp)

  popl %ecx           
  movl $0, %eax
  subl %ecx, %eax
  pushl %eax

  pushl -4(%ebp) 

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 3), %esp

  movl $0, %eax

  leavel
  retl