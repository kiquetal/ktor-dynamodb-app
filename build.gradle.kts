val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.21"
}

group = "me.cresterida"
version = "0.0.1"
application {
    mainClass.set("me.cresterida.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")

}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Deps.ktor_core)
    implementation(Deps.ktor_auth)
    implementation(Deps.ktor_jwt)
    implementation(Deps.ktor_gson)
    implementation(Deps.ktor_netty)
    implementation(Deps.logback)
    testImplementation(Deps.ktor_test)
    testImplementation(Deps.kotlin_test)
    implementation(platform(Deps.aws_bom))
    implementation(Deps.aws_dynamo)
    implementation(Deps.aws_dynamo_enhanced)
    implementation(Deps.ktor_locations)

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}
