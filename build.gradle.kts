plugins {
    kotlin("jvm") version "2.0.21"
}

group = "org.iesra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    dependencies {
        implementation("org.slf4j:slf4j-api:2.0.9")
        implementation("ch.qos.logback:logback-classic:1.5.25")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}