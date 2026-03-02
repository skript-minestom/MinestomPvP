import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    val mcVersion = libs.versions.minestom.get().split("-")[1]
    val date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    val ver = "$date-$mcVersion"
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = project.name
            version = ver
        }
    }

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
