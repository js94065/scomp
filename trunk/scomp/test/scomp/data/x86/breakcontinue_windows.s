STRING_0:
  .ascii "%d\12\0"

decaf_main:
.globl _main
_main:
  enterl $(4 * 1), $0

while_0:
  pushl $1
  popl %eax
  cmpl $0, %eax
  je end_while_0

  movl $0, -4(%ebp)

  pushl $0
  popl -4(%ebp)

while_1:
  pushl -4(%ebp)
  pushl $5
  popl %ecx
  popl %eax
  cmpl %ecx, %eax
  movl $0, %eax
  movl $1, %ecx
  cmovll %ecx, %eax
  pushl %eax

  popl %eax
  cmpl $0, %eax
  je end_while_1

  pushl -4(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 2), %esp

  pushl -4(%ebp)
  pushl $1
  popl %ecx
  popl %eax
  addl %ecx, %eax
  pushl %eax
  popl -4(%ebp)

  pushl $1
  popl %eax
  cmpl $0, %eax
  je else_0
  jmp while_1
  jmp end_if_0
else_0:
end_if_0:

  jmp while_1
end_while_1:

  pushl $1
  popl %eax
  cmpl $0, %eax
  je else_1
  jmp end_while_0
  jmp end_if_1
else_1:
end_if_1:

  jmp while_0
end_while_0:

  movl $0, %eax

  leavel
  retl