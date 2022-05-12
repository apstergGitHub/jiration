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
        maven {
            url = uri("https://packages.atlassian.com/maven-external/")
        }
        maven {
            url = uri("https://maven.atlassian.com/content/repositories/atlassian-public/")
        }
        mavenCentral()
    }

    val kotlinVersion: String by project
    val kotlinxSerializationJsonVersion: String by project

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationJsonVersion")
        implementation("org.springframework.boot:spring-boot-starter-web:2.6.4")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
//        implementation("com.atlassian.jira:jira-api:8.22.2") {
//            exclude("com.octo.captcha")
//            exclude("jndi")
//            exclude("jta")
//        }
//        implementation("com.atlassian.jira:jira-core:8.22.2") {
//            exclude("com.octo.captcha")
//            exclude("jndi")
//            exclude("jta")
//        }
//        implementation("org.hamcrest:hamcrest:2.2")
        implementation("com.atlassian.jira:jira-rest-java-client-core:5.2.4")
        compileOnly("io.atlassian.fugue:fugue:5.0.0")
    }
}

