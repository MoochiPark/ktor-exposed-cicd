package dependencies.test

import Versions

object MockDependencies {
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoJupiter = "org.mockito:mockito-junit-jupiter:${Versions.mockito}"
}

val testImplementation: Configuration by configurations
dependencies {
    testImplementation(MockDependencies.mockitoCore)
    testImplementation(MockDependencies.mockitoJupiter)
}
