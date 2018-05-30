toString:
        push    rbp
        push    rbx
        mov     ebp, edi
        mov     edi, 11
        sub     rsp, 8
        call    malloc
        mov     r8d, ebp
        mov     rbx, rax
        mov     rdi, rax
        mov     ecx, format_int
        mov     edx, 11
        mov     esi, 1
        xor     eax, eax
        call    __sprintf_chk
        add     rsp, 8
        mov     rax, rbx
        pop     rbx
        pop     rbp
        ret

print:
        mov     rdx, rdi
        mov     esi, format_string
        mov     edi, 1
        xor     eax, eax
        jmp     __printf_chk

println:
        jmp     puts


        nop


strCombine:
        push    r14
        push    r13
        mov     r13, rsi
        push    r12
        push    rbp
        mov     r14, rdi
        push    rbx
        call    strlen
        mov     rdi, r13
        mov     rbx, rax
        call    strlen
        lea     rdi, [rbx+rax+1H]
        mov     rbp, rax
        call    malloc
        mov     rdx, rbx
        mov     r12, rax
        mov     rsi, r14
        mov     rdi, rax
        call    memcpy
        lea     rdi, [r12+rbx]
        lea     rdx, [rbp+1H]
        mov     rsi, r13
        call    memcpy
        pop     rbx
        mov     rax, r12
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        ret

getString:
        push    rbx
        mov     edi, 256
        call    malloc
        mov     edi, format_string
        mov     rbx, rax
        mov     rsi, rax
        xor     eax, eax
        call    scanf
        mov     rax, rbx
        pop     rbx
        ret

getInt:
        sub     rsp, 24
        mov     edi, format_int


        mov     rax, qword [fs:abs 28H]
        mov     qword [rsp+8H], rax
        xor     eax, eax
        lea     rsi, [rsp+4H]
        call    scanf
        mov     rdx, qword [rsp+8H]


        xor     rdx, qword [fs:abs 28H]
        mov     eax, dword [rsp+4H]
        jnz     L_001
        add     rsp, 24
        ret

L_001:  call    __stack_chk_fail

parseInt:
        push    rbx
        mov     rbx, rdi
        call    strlen
        test    eax, eax
        jle     L_005
        movsx   edx, byte [rbx]
        lea     ecx, [rdx-30H]
        cmp     cl, 9
        ja      L_005
        lea     esi, [rax-1H]
        mov     rdi, rbx
        xor     eax, eax
        add     rsi, rbx
        jmp     L_003

L_002:  movsx   edx, byte [rdi+1H]
        add     rdi, 1
        lea     ecx, [rdx-30H]
        cmp     cl, 9
        ja      L_004
L_003:  lea     eax, [rax+rax*4]
        cmp     rsi, rdi
        lea     eax, [rdx+rax*2-30H]
        jnz     L_002
L_004:  pop     rbx
        ret

L_005:  xor     eax, eax
        pop     rbx
        ret

ord:
        movsxd  rsi, esi
        movsx   eax, byte [rdi+rsi]
        ret


substring:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        mov     qword [rbp-18H], rdi
        mov     dword [rbp-1CH], esi
        mov     rdx, qword[rbp+10H]
        mov     dword [rbp-20H], edx
        mov     eax, dword [rbp-20H]
        sub     eax, dword [rbp-1CH]
        add     eax, 1
        mov     dword [rbp-0CH], eax
        mov     eax, dword [rbp-0CH]
        add     eax, 1
        cdqe
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-8H], rax
        mov     eax, dword [rbp-0CH]
        movsxd  rdx, eax
        mov     eax, dword [rbp-1CH]
        movsxd  rcx, eax
        mov     rax, qword [rbp-18H]
        add     rcx, rax
        mov     rax, qword [rbp-8H]
        mov     rsi, rcx
        mov     rdi, rax
        call    strncpy
        mov     rax, qword [rbp-8H]
        leave
        ret
