plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("application")
}

dependencies {
    implementation(project(":lib"))

    implementation(libs.bratek20.architecture)
    implementation(libs.bratek20.logs.logback)
}

application {
    mainClass.set("com.github.bratek20.hla.app.Main")
}

tasks.shadowJar {
    archiveClassifier.set("")
    manifest {
        attributes("Main-Class" to "com.github.bratek20.hla.app.Main")
    }
}