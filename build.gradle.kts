plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
}

group = "com.kodehouse"
version = "1.0-SNAPSHOT"

val kotlinxSerializationJsonJvmVersion = "1.8.1"
val kotlinxCoroutinesCoreVersion = "1.10.2"
val ktorClientCoreVersion = "3.3.0"
val ktorClientCioVersion = "3.3.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-serialization-json-jvm
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:${kotlinxSerializationJsonJvmVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${kotlinxCoroutinesCoreVersion}")
    //implementation("io.ktor:ktor-client-core:${ktorClientCoreVersion}")
    //implementation("io.ktor:ktor-client-cio:${ktorClientCioVersion}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}