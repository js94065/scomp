# printab_macosx.s
# FILE=printab_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 15, 12, 2, 16, 11, 8, 13, 5, 6, 3, 7, 17, 4, 14

STRING_0:
	.ascii "%c%c\12\0"

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
	pushq $67
	pushq $0
	popq %rcx
	imulq $8, %rcx
	leaq decaf_a(%rip), %rax
	addq %rcx, %rax
	popq 0(%rax)
	pushq $68
	leaq decaf_b(%rip), %rax
	popq 0(%rax)
	movq $0, -8(%rbp)
	pushq $65
	popq -8(%rbp)
	pushq $0
	popq %rcx
	imulq $8, %rcx
	leaq decaf_a(%rip), %rax
	addq %rcx, %rax
	pushq 0(%rax)
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	pushq $0
	popq %rcx
	imulq $8, %rcx
	leaq decaf_a(%rip), %rax
	addq %rcx, %rax
	pushq 0(%rax)
	leaq decaf_b(%rip), %rax
	pushq 0(%rax)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	movq $0, %rax
	leaveq
	retq

.lcomm decaf_a, 8 * 1
.lcomm decaf_b, 8
