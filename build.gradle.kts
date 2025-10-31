plugins {
    kotlin("jvm") version "2.2.10"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

tasks {
    withType(Test::class) {
        useJUnitPlatform()
    }
}

dependencies {
    testImplementation(kotlin("test"))
}
