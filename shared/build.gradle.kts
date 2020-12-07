import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
    id("com.android.library")
}

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    val kotlinxSerialization = "1.0.1"
    val ktorVersion = "1.4.1"
    val sqlDelightVersion = "1.4.4"
    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.6")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerialization")

                implementation("io.ktor:ktor-client-json:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                // LiveData and ViewModel
                implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")


                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.1.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-json-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
                implementation("io.github.microutils:kotlin-logging:1.7.8")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerialization")

                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")


            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerialization")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$kotlinxSerialization")

                implementation("io.ktor:ktor-client-ios:$ktorVersion")

                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")

            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)