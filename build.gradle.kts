import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.convert") version "1.5.8"

    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("io.ktor.plugin") version "2.2.2"
}

application {
    mainClass.set("com.example.service.ServiceXApplication")
}

springBoot {
    mainClass.set("com.example.ServiceXApplication")
}

group = "com.example"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

//extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.integration:spring-integration-http")
    implementation("org.springframework.integration:spring-integration-kafka")
    implementation("org.springframework.kafka:spring-kafka")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.integration:spring-integration-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.1")
    // https://mvnrepository.com/artifact/io.micrometer/micrometer-registry-prometheus
    implementation("io.micrometer:micrometer-registry-prometheus:1.10.5")
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.0.5")

    // https://mvnrepository.com/artifact/io.projectreactor.kafka/reactor-kafka
    implementation("io.projectreactor.kafka:reactor-kafka:1.3.15")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.6")
    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    testImplementation("ch.qos.logback:logback-classic:1.4.5")
// https://mvnrepository.com/artifact/ch.qos.logback/logback-core
    implementation("ch.qos.logback:logback-core:1.4.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ktor {
    fatJar {
        archiveFileName.set(project.name+"_fat.jar")
    }

    docker {
        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
        localImageName.set(project.name)
        imageTag.set("$version")

        portMappings.set(listOf(
            io.ktor.plugin.features.DockerPortMapping(
                87,
                8087,
                io.ktor.plugin.features.DockerPortMappingProtocol.TCP
            )
        ))
//        externalRegistry.set(
//            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
//                appName = provider { "ktor-app" },
//                username = providers.environmentVariable("DOCKER_HUB_USERNAME"),
//                password = providers.environmentVariable("DOCKER_HUB_PASSWORD")
//            )
    }
}

//tasks.test {
//    outputs.dir(snippetsDir)
//}

//tasks.asciidoctor {
//    inputs.dir(snippetsDir)
//    dependsOn(test)
//}
