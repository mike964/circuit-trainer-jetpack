plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.gmwrokouttimer"
    compileSdk = 36

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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
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
    implementation(libs.androidx.compose.foundation.layout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // View Model
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Core Coil 3 library for Compose
//    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    // Network support (required for Coil 3)
//    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")
    // GIF support - Essential for GIF loading
//    implementation("io.coil-kt.coil3:coil-gif:3.0.4")
    implementation(libs.coil.compose)
    // For GIF support
    implementation(libs.coil.gif)
    // # Icons
    implementation(libs.androidx.compose.material.icons.core)
    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    // Use KSP instead of kapt for the compiler
    ksp(libs.androidx.room.compiler)
    // Optional: Paging 3 integration
    implementation(libs.androidx.room.paging)
    // # Navigation
    implementation(libs.androidx.navigation.compose)
//    implementation("org.jetbrains.kotlinx-serialization:kotlinx-serialization-json:1.6.3")
}

