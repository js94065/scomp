# recursive_macosx.s
# FILE=recursive_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 1, 41, 32, 40, 8, 20, 25, 21, 24, 2, 5, 6, 3, 7, 4

STRING_0:
	.ascii "%d! = %d\12\0"

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

decaf_factorial:
	enterq $(8 * 0), $0
	pushq 16(%rbp)
	pushq $0
	popq %rcx
	popq %rax
	cmpl %ecx, %eax
	movq $0, %rax
	movq $1, %rcx
	cmovleq %rcx, %rax
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je else_0
	pushq $1
	popq %rax
	leaveq
	retq
	jmp end_if_0
else_0:
end_if_0:
	pushq 16(%rbp)
	pushq 16(%rbp)
	pushq $1
	popq %rcx
	popq %rax
	subl %ecx, %eax
	pushq %rax
	callq decaf_factorial
	addq $(8 * 1), %rsp
	pushq %rax
	popq %rcx
	popq %rax
	imull %ecx, %eax
	pushq %rax
	popq %rax
	leaveq
	retq
	leaveq
	retq

decaf_main:
.globl _main
_main:
	enterq $(8 * 1), $0
	movq $0, -8(%rbp)
	pushq $0
	popq -8(%rbp)
	pushq -8(%rbp)
	callq decaf_factorial
	addq $(8 * 1), %rsp
	pushq %rax
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	pushq $5
	popq -8(%rbp)
	pushq -8(%rbp)
	callq decaf_factorial
	addq $(8 * 1), %rsp
	pushq %rax
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_3
	addq $(8 * 3), %rsp
	movq $0, %rax
	leaveq
	retq
