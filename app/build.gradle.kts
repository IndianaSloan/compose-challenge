plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
}

android {
    compileSdk = Build.COMPILE_SDK

    defaultConfig {
        applicationId = Build.PACKAGE_NAME
        minSdk = Build.MIN_SDK
        targetSdk = Build.TARGET_SDK
        versionCode = Build.VERSION_CODE
        versionName = Build.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidX.Compose.VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE_KTX)

    implementation(AndroidX.Compose.ACTIVITY)
    implementation(AndroidX.Compose.CONSTRAINT_LAYOUT)
    implementation(AndroidX.Compose.MATERIAL)
    implementation(AndroidX.Compose.UI)
    implementation(AndroidX.Compose.UI_TOOLING_PREVIEW)
    debugImplementation(AndroidX.Compose.UI_TOOLING)

    implementation(Coil.COMPOSE)

    testImplementation(Test.JUNIT)
    androidTestImplementation(Test.JUNIT_EXT)
    androidTestImplementation(Test.ESPRESSO_CORE)
    androidTestImplementation(Test.COMPOSE_UI_TEST)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}