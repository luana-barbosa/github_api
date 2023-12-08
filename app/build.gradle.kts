plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.luanabarbosa.verity.githubapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.luanabarbosa.verity.githubapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation (project(":data:remote"))
    implementation (project(":toolkit"))

    implementation(Androidx.core)
    implementation(Androidx.appcompat)
    implementation(Androidx.material)
    implementation(Androidx.constraintlayout)
    implementation(Androidx.legacy)
    implementation(Androidx.runtime)

    implementation(Navigation.fragment)
    implementation(Navigation.ui)

    implementation(Picasso.picasso)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gson)

    implementation(Lottie.lottie)

    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.livedata)

    implementation(Koin.android)

    implementation(Coroutines.test)
    implementation(Coroutines.coroutines)

    implementation(Mapper.mapstruct)
    implementation(Mapper.core)

    implementation(OkHttp.core)
    implementation(OkHttp.loggingInterceptor)
    implementation(OkHttp.mockWebserver)

    testImplementation(AndroidTest.junit)
    androidTestImplementation(AndroidTest.testExt)
    androidTestImplementation(AndroidTest.espresso)
    androidTestImplementation(AndroidTest.junit4)
    testImplementation(AndroidTest.mockk)
    testImplementation(AndroidTest.arch)

    //Compose
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}