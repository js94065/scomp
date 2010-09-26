# return_macosx.s
# FILE=return_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 1, 20, 8, 2, 3, 21, 4

STRING_0:
	.ascii "%d\12\0"

callout_printf_2:
	enterq $(8 * 0), $0
	andq $-16, %rsp
	movq 24(%rbp), %rsi
	movq 16(%rbp), %rdi
	movq $0, %rax
	callq _printf
	leaveq
	retq

decaf_get42:
	enterq $(8 * 0), $0
	pushq $42
	popq %rax
	leaveq
	retq
	leaveq
	retq

decaf_main:
.globl _main
_main:
	enterq $(8 * 0), $0
	callq decaf_get42
	addq $(8 * 0), %rsp
	pushq %rax
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	movq $0, %rax
	leaveq
	retq
