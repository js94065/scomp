STRING_0:
  .ascii "%c%c\12\0"

decaf_main:
.globl _main
_main:
  enterl $(4 * 1), $0

  pushl $67
  pushl $0
  popl %ecx
  imull $4, %ecx
  movl $decaf_a, %eax
  addl %ecx, %eax
  popl 0(%eax)

  pushl $68
  movl $decaf_b, %eax
  popl 0(%eax)
  movl $0, -4(%ebp)

  pushl $65 
  popl -4(%ebp)

  pushl $0 
  popl %ecx
  imull $4, %ecx
  movl $decaf_a, %eax
  addl %ecx, %eax
  pushl 0(%eax)

  pushl -4(%ebp)

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 3), %esp

  pushl $0 
  popl %ecx
  imull $4, %ecx
  movl $decaf_a, %eax
  addl %ecx, %eax
  pushl 0(%eax)

  movl $decaf_b, %eax
  pushl 0(%eax)

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 3), %esp

  movl $0, %eax
  leavel
  retl

.lcomm decaf_a, 4 * 1
.lcomm decaf_b, 4
