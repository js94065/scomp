# simple_macosx.s
# FILE=simple_macosx; gcc $FILE.s -o $FILE && ./$FILE
# Applied rules: 2

decaf_main:
.globl _main
_main:
	enterq $(8 * 0), $0
	movq $0, %rax
	leaveq
	retq
