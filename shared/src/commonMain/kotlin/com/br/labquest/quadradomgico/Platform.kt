package com.br.labquest.quadradomgico

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform