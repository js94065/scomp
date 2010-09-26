# ifelse_macosx.s
# FILE=ifelse_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 1, 41, 31, 40, 3, 4, 2, 18, 8

STRING_0:
	.ascii "%d < %d\12\0"

STRING_1:
	.ascii "%d >= %d\12\0"

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

decaf_compare:
	enterq $(8 * 0), $0
	pushq 16(%rbp)
	pushq 24(%rbp)
	popq %rcx
	popq %rax
	cmpl %ecx, %eax
	movq $0, %rax
	movq $1, %rcx
	cmovlq %rcx, %rax
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je else_0
	pushq 24(%rbp)
	pushq 16(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	jmp end_if_0
else_0:
	pushq 24(%rbp)
	pushq 16(%rbp)
	leaq STRING_1(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
end_if_0:
	leaveq
	retq

decaf_main:
.globl _main
_main:
	enterq $(8 * 0), $0
	pushq $2
	pushq $1
	callq decaf_compare
	addq $(8 * 2), %rsp
	pushq $1
	pushq $2
	callq decaf_compare
	addq $(8 * 2), %rsp
	movq $0, %rax
	leaveq
	retq
