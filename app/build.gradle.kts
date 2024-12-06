plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.sagara.finalproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sagara.finalproject"
        minSdk = 25
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
}

dependencies {
    implementation ("com.airbnb.android:lottie:5.2.0")
    implementation ("androidx.gridlayout:gridlayout:1.0.0")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    implementation ("androidx.cardview:cardview:1.0.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}