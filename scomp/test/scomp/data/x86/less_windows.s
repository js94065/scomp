STRING_0:
  .ascii "%d < %d = %d\12\0"

decaf_main:
.globl _main
_main:
  enterl $(4 * 2), $0

  movl $0, -4(%ebp)
  movl $0, -8(%ebp)

  pushl $2
  popl -4(%ebp)

  pushl $7
  popl -8(%ebp)

  pushl -4(%ebp)        
  pushl -4(%ebp)
  popl %ecx
  popl %eax
  cmpl %ecx, %eax
  movl $0, %eax
  movl $1, %ecx
  cmovll %ecx, %eax
  pushl %eax
  pushl -4(%ebp)
  pushl -4(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 4), %esp

  pushl -4(%ebp)        
  pushl -8(%ebp)
  popl %ecx
  popl %eax
  cmpl %ecx, %eax
  movl $0, %eax
  movl $1, %ecx
  cmovll %ecx, %eax
  pushl %eax
  pushl -8(%ebp)
  pushl -4(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 4), %esp

  pushl -8(%ebp)        
  pushl -4(%ebp)
  popl %ecx
  popl %eax
  cmpl %ecx, %eax
  movl $0, %eax
  movl $1, %ecx
  cmovll %ecx, %eax
  pushl %eax
  pushl -4(%ebp)
  pushl -8(%ebp)
  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 4), %esp

  movl $0, %eax

  leavel
  retl