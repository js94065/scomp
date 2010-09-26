# or_macosx.s
# FILE=or_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 2, 5, 6, 9, 10, 3, 39, 7, 4

STRING_0:
	.ascii "%d || %d = %d\12\0"

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
	pushq $1
	popq -8(%rbp)
	pushq $0
	popq -16(%rbp)
	pushq -8(%rbp)
	popq %rax
	cmpq $0, %rax
	jne end_or_0
	pushq -8(%rbp)
	popq %rax
end_or_0:
	pushq %rax
	pushq -8(%rbp)
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	pushq -8(%rbp)
	popq %rax
	cmpq $0, %rax
	jne end_or_1
	pushq -16(%rbp)
	popq %rax
end_or_1:
	pushq %rax
	pushq -16(%rbp)
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	pushq -16(%rbp)
	popq %rax
	cmpq $0, %rax
	jne end_or_2
	pushq -8(%rbp)
	popq %rax
end_or_2:
	pushq %rax
	pushq -8(%rbp)
	pushq -16(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	pushq -16(%rbp)
	popq %rax
	cmpq $0, %rax
	jne end_or_3
	pushq -16(%rbp)
	popq %rax
end_or_3:
	pushq %rax
	pushq -16(%rbp)
	pushq -16(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_4
	addq $(8 * 4), %rsp
	movq $0, %rax
	leaveq
	retq
