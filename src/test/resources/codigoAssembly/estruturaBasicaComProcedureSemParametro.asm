.text
	.global _main

_main:
	pushl %ebp
	movl %esp, %ebp
	call decl
	ret

decl:
	pushl %ebp
	movl %esp, %ebp
	ret

.data

