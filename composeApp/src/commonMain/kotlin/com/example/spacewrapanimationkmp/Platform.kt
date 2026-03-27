package com.example.spacewrapanimationkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform