package me.diocreation.apptemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform