# unaryminus_macosx.s
# FILE=unaryminus_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 2, 5, 6, 8, 3, 22, 7, 4

STRING_0:
	.ascii "-%d = %d\12\0"

callout_printf_3:
	enterq $(8 * 0), $0
	andq $-16, %rsp
	movq 32(%rbp), %rdx
	movq 24(%rbp), %rsi
	movq 16(%rbp), %rdi
	movq $0, %rax
	callq _printf
	leaveq
	retq

decaf_main:
.globl _main
_main:
	enterq $(8 * 1), $0
	movq $0, -8(%rbp)
	pushq $2
	popq -8(%rbp)
	pushq -8(%rbp)
	popq %rcx
	movq $0, %rax
	subl %ecx, %eax
	pushq %rax
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	movq $0, %rax
	leaveq
	retq
