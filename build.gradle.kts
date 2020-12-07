buildscript {
    val kotlinVersion = "1.4.10"
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files

        classpath("com.squareup.sqldelight:gradle-plugin:1.4.3")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.android.tools.build:gradle:4.1.1")
//        classpath("com.android.tools.build:gradle:4.0.1")
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()

        maven (url = "https://kotlin.bintray.com/kotlinx/")
        maven (url = "https://maven.pkg.github.com/chrynan/inject")
        maven (url = "https://dl.bintray.com/chrynan/chrynan")
        maven (url = "https://jitpack.io")
        maven (url = "https://dl.bintray.com/netguru/maven/")
    }
}