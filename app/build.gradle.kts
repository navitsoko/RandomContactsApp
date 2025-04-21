plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools.ksp)
    id("kotlin-parcelize")
    //alias(libs.plugins.compose.compiler)
   // id("kotlin-kapt")
  //  id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.contacts"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.contacts"
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
    viewBinding{
        enable = true
    }

}


dependencies {
    implementation(libs.volley)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.transportation.consumer)
    val room_version = "2.6.1"
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.common)
    //implementation(libs.androidx.room.compiler) // Remove this line
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val nav_version = "2.8.2"

    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:$nav_version")

    // Views/Fragments integration
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Feature module support for Fragments
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")


    //implementation (libs.androidx.navigation.compose)
    //implementation (libs.coil.compose)
    //implementation (libs.androidx.room.runtime)
    //implementation (libs.androidx.room.ktx)
    //implementation (libs.androidx.lifecycle.runtime.compose)
    implementation (libs.androidx.runtime.livedata)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //annotationProcessor (libs.androidx.room.compiler)
    //ksp(libs.androidx.room.compiler)
    implementation("androidx.room:room-runtime:$room_version")
  //  annotationProcessor(libs.androidx.room.compiler)

    // To use Kotlin annotation processing tool (kapt)
    //kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    //ksp(libs.androidx.room.compiler)
    ksp("androidx.room:room-compiler:$room_version")

    //
    api ("com.google.android.material:material:1.1.0-alpha06")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    //implementation ("com.android.volley:volley:1.1.1")
    implementation("com.android.volley:volley:1.1.1")
    implementation ("com.google.code.gson:gson:2.8.5")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.21")
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("androidx.test:core:1.1.0")
    androidTestImplementation ("androidx.test.ext:junit:1.1.0")
    androidTestImplementation ("androidx.test:runner:1.2.0-alpha05")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0-alpha05")
  //  implementation ("com.google.code.gson:gson:2.8.5")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //making HTTP requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Or your preferred version
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // For parsing JSON
    //A powerful HTTP client.
    implementation ("com.squareup.okhttp3:okhttp:4.9.3") // Or your preferred version
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3") // For logging requests


}



