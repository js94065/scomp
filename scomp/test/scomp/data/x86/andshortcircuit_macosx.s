# andshortcircuit_macosx.s
# FILE=andshortcircuit_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 1, 3, 4, 20, 9, 10, 2, 5, 6, 38, 21

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
	leaveq
	retq

decaf_t:
	enterq $(8 * 0), $0
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_1
	addq $(8 * 1), %rsp
	pushq $1
	popq %rax
	leaveq
	retq
	leaveq
	retq

decaf_f:
	enterq $(8 * 0), $0
	leaq STRING_1(%rip), %rax
	pushq %rax
	callq callout_printf_1
	addq $(8 * 1), %rsp
	pushq $0
	popq %rax
	leaveq
	retq
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
	callq decaf_t
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je end_and_0
	callq decaf_t
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
end_and_0:
	pushq %rax
	leaq STRING_2(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	callq decaf_t
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je end_and_1
	callq decaf_f
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
end_and_1:
	pushq %rax
	leaq STRING_3(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	callq decaf_f
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je end_and_2
	callq decaf_t
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
end_and_2:
	pushq %rax
	leaq STRING_4(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	callq decaf_f
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je end_and_3
	callq decaf_f
	addq $(8 * 0), %rsp
	pushq %rax
	popq %rax
end_and_3:
	pushq %rax
	leaq STRING_5(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	movq $0, %rax
	leaveq
	retq
