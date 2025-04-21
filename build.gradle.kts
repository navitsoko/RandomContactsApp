// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
//    dependencies {
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
//    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
   // alias(libs.plugins.devtools.ksp) apply false
   // alias(libs.plugins.compose.compiler) apply false
    id("com.android.library") version "8.1.1" apply false
}