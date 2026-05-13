cmake_minimum_required(VERSION 3.22.1)

project("myapplication")

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Os -finline-functions -fno-exceptions -fno-rtti -fomit-frame-pointer")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -Os -finline-functions -fno-exceptions -fno-rtti -fomit-frame-pointer")

# 禁用 PIC 和 PIE
set(CMAKE_POSITION_INDEPENDENT_CODE OFF)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fno-pic -fno-PIE")
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fno-pic -fno-PIE")

# 链接器选项
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -static -Wl,-Bsymbolic -Wl,-O3 -Wl,--strip-all")
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,-Bsymbolic -Wl,-O3 -Wl,--strip-all")

# 禁用栈保护和缓冲区溢出检查
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fno-stack-protector -fno-builtin")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fno-stack-protector -fno-builtin")

# 禁用 _FORTIFY_SOURCE
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -U_FORTIFY_SOURCE")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -U_FORTIFY_SOURCE")

add_library( # Sets the name of the library.
        tst
        SHARED
        tst.cpp
)
#target_link_libraries(
#        tst
#        hk
#        android
#)

#add_library( # Sets the name of the library.
#        libhk
#        SHARED
#        hk.cpp)

# Specifies libraries libhk should link to.
#target_link_libraries(libhk
#        android
#        log)

# Specifies libraries myapplication should link to.



