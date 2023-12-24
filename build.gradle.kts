plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.16.0"
}

group = "org.example"
version = "1.0.0"

repositories {
    if (System.getenv("CI")?.toBoolean() != true) {
        maven("https://maven.aliyun.com/repository/public")
    }
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

mirai {
    noTestCore = true
    setupConsoleTestRuntime {
        classpath = classpath.filter {
            !it.nameWithoutExtension.startsWith("mirai-core-jvm")
        }
    }
}

dependencies {
    val overflowVersion = "2.16.0-1bf69de-SNAPSHOT"
    compileOnly("top.mrxiaom:overflow-core-api:$overflowVersion")
    testConsoleRuntime("top.mrxiaom:overflow-core:$overflowVersion")
}