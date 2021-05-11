package dependencies.test.ktor

import Versions

object KtorTestDependencies {
    const val ktorServerTests = "io.ktor:ktor-server-tests:${Versions.ktor}"
}

val implementation: Configuration by configurations
dependencies {
    implementation(KtorTestDependencies.ktorServerTests)
}
