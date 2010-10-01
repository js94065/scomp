# modulo_macosx.s
# FILE=modulo_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 2, 5, 6, 8, 3, 27, 7, 4

STRING_0:
	.ascii "%d %% %d = %d\12\0"

callout_printf_4:
	enterq $(8 * 0), $0
	andq $-16, %rsp
	movq 40(%rbp), %rcx
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
	enterq $(8 * 2), $0
	movq $0, -8(%rbp)
	movq $0, -16(%rbp)
	pushq $2
	popq -8(%rbp)
	pushq $7
	popq -16(%rbp)
	pushq -8(%rbp)
	pushq -8(%rbp)
	popq %rcx
	popq %rax
	movq $0, %rdx
	idivl %ecx, %eax
	pushq %rdx
	pushq -8(%rbp)
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	pushq -8(%rbp)
	pushq -16(%rbp)
	popq %rcx
	popq %rax
	movq $0, %rdx
	idivl %ecx, %eax
	pushq %rdx
	pushq -16(%rbp)
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	pushq -16(%rbp)
	pushq -8(%rbp)
	popq %rcx
	popq %rax
	movq $0, %rdx
	idivl %ecx, %eax
	pushq %rdx
	pushq -8(%rbp)
	pushq -16(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	movq $0, %rax
	leaveq
	retq

