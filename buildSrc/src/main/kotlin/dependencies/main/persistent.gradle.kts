package dependencies.main

import Versions


object PersistentDependencies {
    const val postgresql = "org.postgresql:postgresql:${Versions.postgresql}"
    const val h2 = "com.h2database:h2:${Versions.h2}"
    const val exposedJdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}"
    const val exposedCore = "org.jetbrains.exposed:exposed-core:${Versions.exposed}"
    const val exposedDao = "org.jetbrains.exposed:exposed-dao:${Versions.exposed}"
    const val exposedJavaTime = "org.jetbrains.exposed:exposed-java-time:${Versions.exposed}"
    const val hikariCP = "com.zaxxer:HikariCP:${Versions.hikariCP}"
}

val implementation: Configuration by configurations
val runtimeOnly: Configuration by configurations
dependencies {
//    implementation(PersistentDependencies.postgresql)
    implementation(PersistentDependencies.h2)
    implementation(PersistentDependencies.exposedJdbc)
    implementation(PersistentDependencies.exposedCore)
    implementation(PersistentDependencies.exposedDao)
    implementation(PersistentDependencies.exposedJavaTime)
    implementation(PersistentDependencies.hikariCP)
}
