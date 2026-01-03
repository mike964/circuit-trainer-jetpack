plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.gmwrokouttimer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gmwrokouttimer"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        ksp {
//            arg("room.schemaLocation", "$projectDir/schemas")
//        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // View Model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")

    // Core Coil 3 library for Compose
//    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    // Network support (required for Coil 3)
//    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")
    // GIF support - Essential for GIF loading
//    implementation("io.coil-kt.coil3:coil-gif:3.0.4")
    implementation("io.coil-kt:coil-compose:2.7.0")
    // For GIF support
    implementation("io.coil-kt:coil-gif:2.7.0")
    // # Icons
    implementation("androidx.compose.material:material-icons-core:1.7.8")
    // Room Database
    val roomVersion = "2.7.0" // Or latest stable 2.8.x in 2026
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    // Use KSP instead of kapt for the compiler
    ksp("androidx.room:room-compiler:$roomVersion")
    // Optional: Paging 3 integration
    implementation("androidx.room:room-paging:$roomVersion")
    // # Navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
//    implementation("org.jetbrains.kotlinx-serialization:kotlinx-serialization-json:1.6.3")
}

