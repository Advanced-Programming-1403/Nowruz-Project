plugins {
    id ("application")
    id ("java")
}

group = "com.project.lyrics"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.google.code.gson:gson:2.10.1")
}

application {
    mainClass = "org.example.Main"
}

