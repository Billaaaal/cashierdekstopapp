import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation ("io.socket:socket.io-client:2.0.0")
                implementation("com.squareup.okhttp3:okhttp:4.10.0")
                implementation("com.google.code.gson:gson:2.10")
                implementation ("com.squareup.retrofit2:retrofit:2.9.0" )//2.7.1)
                implementation("com.squareup.retrofit2:converter-gson:2.9.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.1")
                implementation ("org.json:json:20220924")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
                implementation("fr.bmartel:jspeedtest:1.32.1")
                implementation ("com.github.kittinunf.fuel:fuel:2.3.1")
                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
                implementation("io.ktor:ktor-network:2.2.3")
                implementation("io.ktor:ktor-client-core:2.2.3")
                implementation("io.ktor:ktor-client-cio:2.2.3")




            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "cashierdekstopapp"
            packageVersion = "1.0.0"
        }
    }
}
