buildscript {

    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.3.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.22" apply false
    id("com.android.library") version "8.3.2" apply false
}