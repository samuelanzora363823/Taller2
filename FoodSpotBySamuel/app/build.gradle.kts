plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.pdmtaller2.t00363823_SamuelAnzora"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pdmtaller2.t00363823_SamuelAnzora"
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
    // Coil para imágenes
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Core y lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    implementation ("androidx.navigation:navigation-compose:2.7.5")

    // Material 3 (Ya lo tienes, no lo borres)
    implementation(libs.androidx.material3)

    // **Material 2 (Lo que agregamos para usar TopAppBar, IconButton, etc.)**
    implementation("androidx.compose.material:material:1.4.0")  // Material 2

    // Compose UI (incluye básico + gráficos + tooling)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // 🔥 FUNDAMENTAL: Para LazyRow, Column, Scroll, etc.
    implementation("androidx.compose.foundation:foundation")
    implementation(libs.androidx.material.icons.core.android)

    // Pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
