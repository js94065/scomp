STRING_0:
  .ascii "t\12\0"

STRING_1:
  .ascii "f\12\0"

STRING_2:
  .ascii "1 && 1 = %d\12\0"

STRING_3:
  .ascii "1 && 0 = %d\12\0"

STRING_4:
  .ascii "0 && 1 = %d\12\0"

STRING_5:
  .ascii "0 && 0 = %d\12\0"

decaf_t:
  enterl $(4 * 0), $0

  movl $STRING_0, %eax
  pushl %eax
  calll _printf
  addl $(4 * 1), %esp

  pushl $1
  popl %eax
  leavel
  retl

  leavel
  retl

decaf_f:
  enterl $(4 * 0), $0

  movl $STRING_1, %eax
  pushl %eax
  calll _printf
  addl $(4 * 1), %esp

  pushl $0
  popl %eax
  leavel
  retl

  leavel
  retl

decaf_main:
.globl _main
_main:
  enterl $(4 * 0), $0

  calll decaf_t
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
  cmpl $0, %eax
  je end_and_0
  calll decaf_t
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
end_and_0:
  pushl %eax

  movl $STRING_2, %eax
  pushl %eax

  calll _printf
  addl $(4 * 2), %esp

  calll decaf_t
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
  cmpl $0, %eax
  je end_and_1
  calll decaf_f
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
end_and_1:
  pushl %eax

  movl $STRING_3, %eax
  pushl %eax

  calll _printf
  addl $(4 * 2), %esp

  calll decaf_f
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
  cmpl $0, %eax
  je end_and_2
  calll decaf_t
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
end_and_2:
  pushl %eax

  movl $STRING_4, %eax
  pushl %eax

  calll _printf
  addl $(4 * 2), %esp

  calll decaf_f
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
  cmpl $0, %eax
  je end_and_3
  calll decaf_f
  addl $(4 * 0), %esp
  pushl %eax
  popl %eax
end_and_3:
  pushl %eax

  movl $STRING_5, %eax
  pushl %eax

  calll _printf
  addl $(4 * 2), %esp

  movl $0, %eax

  leavel
  retl