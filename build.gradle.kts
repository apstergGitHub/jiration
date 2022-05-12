plugins {
    val kotlinVersion = "1.6.10"
    kotlin("jvm") version kotlinVersion apply false
    id("org.springframework.boot") version "2.6.4" apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    `java-library`
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}

subprojects {
    group = "com.jiration"
    version = "DEV-SNAPSHOT"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply<JavaLibraryPlugin>()

    repositories {
        mavenCentral()
        maven {
            url = uri("https://packages.atlassian.com/maven-external/")
        }
    }

    val kotlinVersion: String by project
    val kotlinxSerializationJsonVersion: String by project

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationJsonVersion")
        implementation("org.springframework.boot:spring-boot-starter-web:2.6.4")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    }
}

