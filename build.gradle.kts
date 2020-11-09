plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    testCompile("org.assertj", "assertj-core", "3.12.0")
    testCompile("org.junit.jupiter", "junit-jupiter-api", "5.4.0")
    testCompile("org.junit.jupiter", "junit-jupiter-params", "5.4.0")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.4.0")
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
    useJUnitPlatform()
}
