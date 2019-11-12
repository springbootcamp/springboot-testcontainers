import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") version Versions.Build.kotlin
    kotlin("plugin.jpa")  version Versions.Build.kotlin
    kotlin("plugin.allopen")  version Versions.Build.kotlin
    kotlin("kapt")
    id("org.springframework.boot") version Versions.springBoot
}

java.sourceCompatibility = Versions.Build.javaVersion

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}


dependencies {
    implementation(
        platform(SpringBootPlugin.BOM_COORDINATES)
    )
    implementation(
        platform("org.jetbrains.kotlin:kotlin-bom")
    )

    fun springBoot(module:String) = "org.springframework.boot:spring-boot-$module"

    implementation(springBoot("starter-web"))
    implementation(springBoot("starter-data-jpa"))
    implementation(springBoot("starter-mustache"))

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    runtimeOnly("com.h2database:h2:1.4.200") // See https://github.com/spring-projects/spring-boot/issues/18593 and https://github.com/h2database/h2database/issues/1841
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    //kapt(springBoot("configuration-processor"))
    //kapt("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation(springBoot("starter-test")) {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:1.1.3")

}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.Build.java
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }

}