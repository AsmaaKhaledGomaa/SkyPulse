plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.asoom.skypulse"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.asoom.skypulse"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    // Core Android + Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //  Lifecycle
    implementation(libs.bundles.lifecycle)

    // Kotlin Coroutines
    implementation(libs.bundles.coroutines)

    // Networking - Ktor
    implementation(libs.bundles.ktor)

    // Dependency Injection - Koin
    implementation(libs.bundles.koin)

    // Android Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    implementation ("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.palette:palette:1.0.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Location Permission
    implementation(libs.accompanist.permissions)
    implementation(libs.play.services.location)
}