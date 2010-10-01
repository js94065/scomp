# breakcontinue_macosx.s
# FILE=breakcontinue_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 2, 42, 9, 5, 6, 8, 31, 7, 3, 4, 23, 41, 44, 43

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

decaf_main:
.globl _main
_main:
	enterq $(8 * 1), $0
while_0:
	pushq $1
	popq %rax
	cmpq $0, %rax
	je end_while_0
	movq $0, -8(%rbp)
	pushq $0
	popq -8(%rbp)
while_1:
	pushq -8(%rbp)
	pushq $5
	popq %rcx
	popq %rax
	cmpl %ecx, %eax
	movq $0, %rax
	movq $1, %rcx
	cmovlq %rcx, %rax
	pushq %rax
	popq %rax
	cmpq $0, %rax
	je end_while_1
	pushq -8(%rbp)
	leaq STRING_0(%rip), %rax
	pushq %rax
	callq callout_printf_2
	addq $(8 * 2), %rsp
	pushq -8(%rbp)
	pushq $1
	popq %rcx
	popq %rax
	addl %ecx, %eax
	pushq %rax
	popq -8(%rbp)
	pushq $1
	popq %rax
	cmpq $0, %rax
	je else_0
	jmp while_1
	jmp end_if_0
else_0:
end_if_0:
	jmp while_1
end_while_1:
	pushq $1
	popq %rax
	cmpq $0, %rax
	je else_1
	jmp end_while_0
	jmp end_if_1
else_1:
end_if_1:
	jmp while_0
end_while_0:
	movq $0, %rax
	leaveq
	retq
