STRING_0:
  .ascii "%d! = %d\12\0"

decaf_factorial:
  enterl $(4 * 0), $0

  pushl 8(%ebp)
  pushl $0
  popl %ecx
  popl %eax
  cmpl %ecx, %eax
  movl $0, %eax
  movl $1, %ecx
  cmovlel %ecx, %eax
  pushl %eax
  popl %eax
  cmpl $0, %eax
  je else_0
  pushl $1
  popl %eax
  leavel
  retl
  jmp end_if_0
else_0:
end_if_0:
  pushl 8(%ebp)
  pushl 8(%ebp)
  pushl $1
  popl %ecx
  popl %eax
  subl %ecx, %eax
  pushl %eax
  calll decaf_factorial
  addl $(4 * 1), %esp
  pushl %eax
  popl %ecx
  popl %eax
  imull %ecx, %eax
  pushl %eax
  popl %eax
  leavel
  retl

  leavel
  retl

decaf_main:
.globl _main
_main:
  enterl $(4 * 1), $0

  movl $0, -4(%ebp)

  pushl $0
  popl -4(%ebp)

  pushl -4(%ebp)
  calll decaf_factorial
  addl $(4 * 1), %esp
  pushl %eax
  pushl -4(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 3), %esp

  pushl $5
  popl -4(%ebp)

  pushl -4(%ebp)
  calll decaf_factorial
  addl $(4 * 1), %esp
  pushl %eax
  pushl -4(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 3), %esp

  movl $0, %eax

  leavel
  retl