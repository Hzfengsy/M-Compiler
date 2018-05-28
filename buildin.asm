toString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32
        mov     dword [rbp-14H], edi
        mov     edi, 11
        call    malloc
        mov     qword [rbp-8H], rax
        mov     edx, dword [rbp-14H]
        mov     rax, qword [rbp-8H]
        mov     esi, format_int
        mov     rdi, rax
        mov     eax, 0
        call    sprintf
        mov     rax, qword [rbp-8H]
        leave
        ret

print:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rsi, rax
        mov     edi, format_string
        mov     eax, 0
        call    printf
        nop
        leave
        ret

println:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rdi, rax
        call    puts
        nop
        leave
        ret

        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     qword [rbp-8H], rdi
        mov     rax, qword [rbp-8H]
        mov     rdi, rax
        call    puts
        nop
        leave
        ret


strCombine:
        push    rbp
        mov     rbp, rsp
        push    rbx
        sub     rsp, 40
        mov     qword [rbp-28H], rdi
        mov     qword [rbp-30H], rsi
        mov     rax, qword [rbp-28H]
        mov     rdi, rax
        call    strlen
        mov     rbx, rax
        mov     rax, qword [rbp-30H]
        mov     rdi, rax
        call    strlen
        add     rax, rbx
        add     rax, 1
        mov     rdi, rax
        call    malloc
        mov     qword [rbp-18H], rax
        mov     rdx, qword [rbp-28H]
        mov     rax, qword [rbp-18H]
        mov     rsi, rdx
        mov     rdi, rax
        call    strcpy
        mov     rdx, qword [rbp-30H]
        mov     rax, qword [rbp-18H]
        mov     rsi, rdx
        mov     rdi, rax
        call    strcat
        mov     rax, qword [rbp-18H]
        add     rsp, 40
        pop     rbx
        pop     rbp
        ret

        push    rbp
        mov     rbp, rsp
        sub     rsp, 32

getString:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 32


        mov     rax, qword [fs:abs 28H]
        mov     qword [rbp-8H], rax
        xor     eax, eax
        mov     edi, 256
        call    malloc
        mov     qword [rbp-18H], rax
        mov     qword [rbp-10H], 256
        mov     rdx, qword [rel stdin]
        lea     rcx, [rbp-10H]
        lea     rax, [rbp-18H]
        mov     rsi, rcx
        mov     rdi, rax
        call    getline
        mov     rax, qword [rbp-18H]
        mov     rsi, qword [rbp-8H]


        xor     rsi, qword [fs:abs 28H]
        jz      L_001
        call    __stack_chk_fail
L_001:  leave
        ret

getInt:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16


        mov     rax, qword [fs:abs 28H]
        mov     qword [rbp-8H], rax
        xor     eax, eax
        lea     rax, [rbp-0CH]
        mov     rsi, rax
        mov     edi, format_int
        mov     eax, 0
        call    scanf
        mov     eax, dword [rbp-0CH]
        mov     rdx, qword [rbp-8H]


        xor     rdx, qword [fs:abs 28H]
        jz      L_002
        call    __stack_chk_fail
L_002:  leave
        ret
