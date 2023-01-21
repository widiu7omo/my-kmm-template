package me.diocreation.apptemplate.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform