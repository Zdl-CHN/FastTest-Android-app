/*
#include <unistd.h>
#include <fcntl.h>
#include <cstdio>
#include <cstdint>
#include <cstring>
#include <string>
#include <sys/syscall.h>
#pragma GCC optimize("O0")
#include <ctime>
__attribute__((naked,noinline)) long m_syscall(long call_num, ...) {
    __asm__ __volatile__(
            "mov x8, x0\n\t"   // 将 call_num 存入 x8（系统调用号）
            "mov x0, x1\n\t"
            "mov x1, x2\n\t"
            "mov x2, x3\n\t"
            "mov x3, x4\n\t"
            "mov x4, x5\n\t"
            "mov x5, x6\n\t"
            "svc #0\n\t"
            "ret\n\t"
            :
            :
            : "x0", "x1", "x2", "x3", "x4", "x5", "x8" // clobber列表 被修改的寄存器
            );
}
void invoke();
char* getTime();
int sys_open(const char *filePath);
size_t sys_write(int fd, const void* buffer, size_t count);
long int sys_close(int fd);

extern "C" void ssl_write_inject() {

    __asm__ volatile(
            "sub sp, sp, #32;"
            "stp x0, x1, [sp, #0];"
            "stp x2, xzr, [sp, #16];"
            );
    invoke();

    __asm__ volatile(
            "ldp x0, x1, [sp, #0];"
            "ldp x2, x3, [sp, #16];"
            "add sp, sp, #32;"
            );
    __asm__ volatile("NOP;");   //预留nop 跳转

}
void t(){

}
 void invoke() {
    // x0, x1, x2是64位寄存器，使用uint64_t
    uint64_t x0_value,  x2_bfSize;
    const void *x1_buffer;

    // 使用内联汇编从寄存器获取值
    __asm__ volatile("mov %0, x0\n" // 将x0的值移动到x0_value
                     "mov %1, x1\n" // 将x1的值移动到x1_value
                     "mov %2, x2\n" // 将x2的值移动到x2_value
            : "=r"(x0_value), "=r"(x1_buffer), "=r"(x2_bfSize) // 输出操作数
            :                // 没有输入操作数
            : "x0", "x1", "x2" //  clobber 列表，描述可能被破坏的寄存器
            );
    char* time_str= getTime();
    char logFile[256];
    snprintf(logFile,
             sizeof(logFile),
             "%s%s%s",
             "/sdcard/Android/data/com.zhiliaoapp.musically/sslWriteBF_",
             time_str,
             ".txt");

    free(time_str); // 释放内存
    int fd = sys_open(logFile);
    sys_write(fd, x1_buffer, x2_bfSize);
    // 关闭文件
    sys_close(fd);
}


char* getTime() {
    struct timespec ts{};
    m_syscall(SYS_clock_gettime, CLOCK_REALTIME, &ts);
    char* buffer = (char*)malloc(20); // 动态分配内存
    snprintf(buffer, 20, "%ld", ts.tv_sec);

    return buffer;
}
int sys_open(const char* filePath){return  m_syscall(SYS_openat, AT_FDCWD, filePath, O_WRONLY | O_CREAT | O_APPEND, 0644);}
size_t sys_write(int fd, const void* buffer, size_t count) {return m_syscall(SYS_write, fd, buffer, count);}
long int sys_close(int fd){return  m_syscall(SYS_close, fd);}

//void my_snprintf(char *dest, size_t size, const char *format, ...) {
//    va_list args;
//    // 初始化可变参数列表
//    va_start(args, format);
//
//    // 使用vsnprintf进行格式化，并确保不会溢出dest
//    vsnprintf(dest, size, format, args);
//
//    // 清理可变参数列表
//    va_end(args);
//
//    // 确保字符串以null结尾
//    if (size > 0) {
//        dest[size - 1] = '\0';
//    }
//}*/
