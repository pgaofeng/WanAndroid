buildscript {

    extra["kotlin_version"] = "1.4.21"
    extra["hilt_version"] = "2.30.1-alpha"

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlin_version"]}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hilt_version"]}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}