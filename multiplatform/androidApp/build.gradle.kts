plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "me.diocreation.apptemplate.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "me.diocreation.apptemplate.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildToolsVersion = "33.0.1"
}

dependencies {
    implementation(project(":shared"))
    val composeBom = platform("androidx.compose:compose-bom:2023.01.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")

    //Accompanist
    val accompanistVersion = "0.28.0"
    implementation("com.google.accompanist:accompanist-pager:${accompanistVersion}")
    implementation("com.google.accompanist:accompanist-insets:${accompanistVersion}")
    implementation("com.google.accompanist:accompanist-navigation-animation:${accompanistVersion}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")
    implementation("com.google.accompanist:accompanist-placeholder-material:${accompanistVersion}")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.22.0-rc")
    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    // Koin-Dependency injection
    implementation("io.insert-koin:koin-android:3.3.2")
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")
    //Compose navigation various screen
    implementation("androidx.navigation:navigation-compose:2.6.0-alpha04")
    //Image processing
    implementation("io.coil-kt:coil-compose:2.2.2")
}