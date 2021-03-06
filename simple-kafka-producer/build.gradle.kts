plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    implementation("org.apache.kafka:kafka-clients:2.5.0")
    implementation("org.slf4j:slf4j-simple:1.7.30")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
