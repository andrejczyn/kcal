import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("maven-publish")
    kotlin("plugin.spring") version "1.3.72"
    // Apply the application plugin to add support for building a CLI application.
    application
}

group = "com.andrejczyn"
version = "0.2.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application.
    mainClassName = "com.andrejczyn.kcal.KCalAppKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

configurations {
    listOf(configurations.apiElements, configurations.runtimeElements).forEach() {
        it.get().outgoing.artifacts.removeIf { it.buildDependencies.getDependencies(null).contains(tasks.getByName("jar")) }
        //it.outgoing.artifact()
    }
}

publishing {
    publications {
        create<MavenPublication>("GitHub") {
            groupId = "com.andrejczyn"
            artifactId = "kcal"
            version = "0.2.0.SNAPSHOT"

            from(components["java"])
        }
    }
    repositories {
        maven {
//            name = "GitHub"
            url = uri("https://maven.pkg.github.com/andrejczyn/kcal")
            credentials {
                username="andrejczyn"
            }
        }
    }
}
