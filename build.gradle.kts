plugins {
    id("java")
}

group = "org.kalah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.5.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.5.0")
}

tasks.register<JavaExec>("run") {
    group = "application"
    description = "Runs the application with an interactive console"

    dependsOn("build")

    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    mainClass.set("org.kalah.ConsoleGameRunner")
}

tasks.test {
    useJUnitPlatform()
}