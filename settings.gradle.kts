pluginManagement {
    val kotlinVersion: String by settings
    val ktLintPluginVersion: String by settings

    plugins {
        id("org.jlleitschuh.gradle.ktlint") version ktLintPluginVersion
    }
}

rootProject.name = "ktor-exposed-cicd"
include("app")
