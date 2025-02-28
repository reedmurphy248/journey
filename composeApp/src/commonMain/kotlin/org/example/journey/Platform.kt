package org.example.journey

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform