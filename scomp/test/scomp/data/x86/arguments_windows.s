STRING_0:
  .ascii "0\0"

STRING_1:
  .ascii "%d\12\0"

STRING_2:
  .ascii "%d %d\12\0"

STRING_3:
  .ascii "%d %d %d %d %d %d %d %d %d\12\0"

decaf_f0:
  enterl $(4 * 0), $0

  movl $STRING_0, %eax
  pushl %eax
  calll _atoi
  addl $(4 * 1), %esp
  pushl %eax
  movl $STRING_1, %eax
  pushl %eax
  calll _printf
  addl $(4 * 2), %esp

  leavel
  retl

decaf_f1:
  enterl $(4 * 0), $0

  pushl 8(%ebp)
  popl 8(%ebp)

  pushl 8(%ebp)
  movl $STRING_1, %eax
  pushl %eax
  calll _printf
  addl $(4 * 2), %esp

  leavel
  retl

decaf_f2:
  enterl $(4 * 0), $0

  pushl 12(%ebp)
  pushl 8(%ebp)
  movl $STRING_2, %eax
  pushl %eax
  calll _printf
  addl $(4 * 3), %esp

  leavel
  retl

decaf_f9:
  enterl $(4 * 0), $0

  pushl 40(%ebp)
  pushl 36(%ebp)
  pushl 32(%ebp)
  pushl 28(%ebp)
  pushl 24(%ebp)
  pushl 20(%ebp)
  pushl 16(%ebp)
  pushl 12(%ebp)
  pushl 8(%ebp)
  movl $STRING_3, %eax
  pushl %eax
  calll _printf
  addl $(4 * 10), %esp

  leavel
  retl

decaf_main:
.globl _main
_main:
  enterl $(4 * 0), $0

  calll decaf_f0
  addl $(4 * 0), %esp

  pushl $1
  calll decaf_f1
  addl $(4 * 1), %esp

  pushl $3
  pushl $2
  calll decaf_f2
  addl $(4 * 2), %esp

  pushl $12
  pushl $11
  pushl $10
  pushl $9
  pushl $8
  pushl $7
  pushl $6
  pushl $5
  pushl $4
  calll decaf_f9
  addl $(4 * 9), %esp

  movl $0, %eax

  leavel
  retl