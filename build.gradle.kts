plugins {
    java
    `maven-publish`
}

group = "io.github.togar2"
description = "Minecraft combat library for Minestom, with support for both 1.9+ and 1.8 combat"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.minestom)
    testImplementation(libs.minestom)
    compileOnly(libs.fastutil)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    onlyIf { false }
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.hapily.me/releases/")
            credentials {
                username = System.getenv("REPO_HAPILY_USERNAME")
                password = System.getenv("REPO_HAPILY_PASSWORD")
            }
        }
    }
}
