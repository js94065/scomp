# call_macosx.s
# FILE=call_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 1, 3, 4, 2, 18

STRING_0:
	.ascii "42\12\0"

callout_printf_1:
	enterq $(8 * 0), $0
	andq $-16, %rsp
	movq 16(%rbp), %rdi
	movq $0, %rax
	callq _printf
	leaveq
	retq

decaf_print:
	enterq $(8 * 0), $0
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_1
	addq $(8 * 1), %rsp
	leaveq
	retq

decaf_main:
.globl _main
_main:
	enterq $(8 * 0), $0
	callq decaf_print
	addq $(8 * 0), %rsp
	movq $0, %rax
	leaveq
	retq
