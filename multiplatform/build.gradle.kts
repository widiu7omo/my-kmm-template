buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
        classpath("com.android.tools.build:gradle:7.4.0")
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:0.13.3")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-appdistribution-gradle:3.2.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    kotlin("android").version("1.7.21").apply(false)
    kotlin("multiplatform").version("1.7.21").apply(false)
    id("org.jetbrains.kotlin.jvm") version "1.7.21" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
