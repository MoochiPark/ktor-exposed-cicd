package conventions

import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // --# Gradle
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.kapt")

    // --# Project
    id("dependencies.main.kotlin")
    id("dependencies.main.apache-commons")
    id("dependencies.main.arrow")
    id("dependencies.main.configure")
    id("dependencies.main.jackson")
    id("dependencies.main.logger")
    id("dependencies.main.persistent")

    id("dependencies.main.ktor.server")

    id("dependencies.test.assert")
    id("dependencies.test.junit")
    id("dependencies.test.kotest")
    id("dependencies.test.mock")

    id("dependencies.test.ktor.server-tests")

}

repositories {
    google()
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    reports.junitXml.isEnabled = true
    testLogging {
        events = setOf(FAILED, PASSED, SKIPPED)
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "7.0"
    distributionType = Wrapper.DistributionType.BIN
}

val jar by tasks.getting(Jar::class) {
    doFirst {
        from(configurations.compileClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
    exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes["Main-Class"] = "io.wisoft.ktor.ApplicationKt"
    }
}
