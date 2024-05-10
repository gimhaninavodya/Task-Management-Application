plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.example.task_management_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.task_management_app"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding = true
    }
}

dependencies {

    // Kotlin stdlib
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // ROOM
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // Kotlin Room extensions
    implementation ("androidx.room:room-ktx:$roomVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Navigation
    val navVersion = "2.7.5"
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    //noinspection GradleDependency
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Life Cycle Arch
    val lifecycleVersion = "2.3.1"

    // ViewModel
    //noinspection GradleDependency
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // LiveData
    //noinspection GradleDependency
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    // Annotation processor
    //noinspection LifecycleAnnotationProcessorWithJava8,GradleDependency
    ksp("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
}
