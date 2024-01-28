plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.otus.spring"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /** Spring **/
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")

    /** Liquibase */
    implementation("org.liquibase:liquibase-core:4.25.0")

    /** Lombok **/
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    /** Postgresql **/
    runtimeOnly("org.postgresql:postgresql")

    /** Tests **/
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}
