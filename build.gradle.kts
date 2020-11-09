import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.assertj", "assertj-core", "3.12.0")
    testImplementation("org.junit.jupiter", "junit-jupiter", "5.7.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

configure<ApplicationPluginConvention> {
    mainClassName = "com.example.validation.Main"
}

tasks.withType<Test> {
    failFast = true
    useJUnitPlatform {
        testLogging {
            events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED)
        }
    }
}
