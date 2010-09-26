# arguments_macosx.s
# FILE=arguments_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 1, 3, 4, 40, 2, 18, 8

STRING_0:
	.ascii "0\12\0"

STRING_1:
	.ascii "%d\12\0"

STRING_2:
	.ascii "%d %d\12\0"

STRING_3:
	.ascii "%d %d %d %d %d %d %d %d %d\12\0"

callout_printf_1:
	enterq $(8 * 0), $0
	andq $-16, %rsp
	movq 16(%rbp), %rdi
	movq $0, %rax
	callq _printf
	leaveq
	retq

callout_printf_2:
	enterq $(8 * 0), $0
	andq $-16, %rsp
	movq 24(%rbp), %rsi
	movq 16(%rbp), %rdi
	movq $0, %rax
	callq _printf
	addq $(8 * 0), %rsp
	leaveq
	retq

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

callout_printf_10:
	enterq $(8 * 4), $0
	andq $-16, %rsp
	addq $(8 * 4), %rsp
	pushq 88(%rbp)
	pushq 80(%rbp)
	pushq 72(%rbp)
	pushq 64(%rbp)
	movq 56(%rbp), %r9
	movq 48(%rbp), %r8
	movq 40(%rbp), %rcx
	movq 32(%rbp), %rdx
	movq 24(%rbp), %rsi
	movq 16(%rbp), %rdi
	movq $0, %rax
	callq _printf
	leaveq
	retq

decaf_f0:
	enterq $(8 * 0), $0
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_1
	addq $(8 * 1), %rsp
	addq $(8 * 1), %rsp
	leaveq
	retq

decaf_f1:
	enterq $(8 * 0), $0
	pushq 16(%rbp)
	leaq STRING_1(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	leaveq
	retq

decaf_f2:
	enterq $(8 * 0), $0
	pushq 24(%rbp)
	pushq 16(%rbp)
	leaq STRING_2(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	leaveq
	retq

decaf_f9:
	enterq $(8 * 0), $0
	pushq 80(%rbp)
	pushq 72(%rbp)
	pushq 64(%rbp)
	pushq 56(%rbp)
	pushq 48(%rbp)
	pushq 40(%rbp)
	pushq 32(%rbp)
	pushq 24(%rbp)
	pushq 16(%rbp)
	leaq STRING_3(%rip), %rax
	pushq %rax
	callq callout_printf_10
	addq $(8 * 10), %rsp
	leaveq
	retq

decaf_main:
.globl _main
_main:
	enterq $(8 * 0), $0
	callq decaf_f0
	addq $(8 * 0), %rsp
	pushq $1
	callq decaf_f1
	addq $(8 * 1), %rsp
	pushq $3
	pushq $2
	callq decaf_f2
	addq $(8 * 2), %rsp
	pushq $12
	pushq $11
	pushq $10
	pushq $9
	pushq $8
	pushq $7
	pushq $6
	pushq $5
	pushq $4
	callq decaf_f9
	addq $(8 * 9), %rsp
	movq $0, %rax
	leaveq
	retq
