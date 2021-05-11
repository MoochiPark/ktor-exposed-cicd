plugins {
    // --#Gradle
    id("org.jlleitschuh.gradle.ktlint")

    // --#Project
    id("conventions.kotlin-application")
    kotlin("plugin.serialization") version "1.5.0"
}
