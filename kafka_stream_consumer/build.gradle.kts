/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
	id("com.google.cloud.tools.jib") version "3.1.4"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")

    implementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    implementation("org.junit.jupiter:junit-jupiter:5.7.2")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.drools:drools-core:7.66.0.Final")
	implementation("org.drools:drools-metric:7.66.0.Final")
    implementation("org.drools:drools-compiler:7.66.0.Final")
    implementation("org.drools:drools-model-compiler:7.66.0.Final")
    implementation("org.drools:drools-decisiontables:7.66.0.Final")
    implementation("ch.qos.logback:logback-classic:1.1.+")
    implementation("org.slf4j:slf4j-api:1.7.+")
}

application {
    // Define the main class for the application.
    mainClass.set("com.sample.application.App")
}

tasks.test {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

jib {
	container {
		creationTime = "USE_CURRENT_TIMESTAMP"
	}
	from {
		image = "azul/zulu-openjdk:11"
	}
	to {
		image = "gradle_test"
	}
}
