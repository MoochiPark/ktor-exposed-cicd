package dependencies.main.ktor

import Versions

object KtorDependencies {
    const val ktorServerCore = "io.ktor:ktor-server-core:${Versions.ktor}"
    const val ktorServerNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-serialization:${Versions.ktor}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(KtorDependencies.ktorServerCore)
    implementation(KtorDependencies.ktorServerNetty)
    implementation(KtorDependencies.ktorSerialization)
}
