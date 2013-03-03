STRING_0:
  .ascii "%d && %d = %d\12\0"

decaf_main:
.globl _main
_main:
  enterl $(4 * 2), $0

  movl $0, -4(%ebp)
  movl $0, -8(%ebp)

  pushl $1
  popl -4(%ebp)

  pushl $0
  popl -8(%ebp)

  pushl -4(%ebp) 
  popl %eax
  cmpl $0, %eax
  je end_and_0
  pushl -4(%ebp)
  popl %eax
end_and_0:
  pushl %eax

  pushl -4(%ebp)
  pushl -4(%ebp)

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 4), %esp

  pushl -4(%ebp) 
  popl %eax
  cmpl $0, %eax
  je end_and_1
  pushl -8(%ebp)
  popl %eax
end_and_1:
  pushl %eax

  pushl -8(%ebp)
  pushl -4(%ebp)

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 4), %esp

  pushl -8(%ebp) 
  popl %eax
  cmpl $0, %eax
  je end_and_2
  pushl -4(%ebp)
  popl %eax
end_and_2:
  pushl %eax

  pushl -4(%ebp)
  pushl -8(%ebp)

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 4), %esp

  pushl -8(%ebp) 
  popl %eax
  cmpl $0, %eax
  je end_and_3
  pushl -8(%ebp)
  popl %eax
end_and_3:
  pushl %eax

  pushl -8(%ebp)
  pushl -8(%ebp)

  movl $STRING_0, %eax
  pushl %eax

  calll _printf
  addl $(4 * 4), %esp

  movl $0, %eax

  leavel
  retl