plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.ultimocuatri"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ultimocuatri"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.core:core-ktx:1.12.0") // O una versión más reciente
    implementation("androidx.appcompat:appcompat:1.6.1") // O una versión más reciente
    implementation("com.google.android.material:material:1.11.0") // O una versión más reciente
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // O una versión más reciente

    // Asegúrate de que esta dependencia (o una más reciente) esté presente:
    implementation("androidx.activity:activity-ktx:1.8.2") // O la versión más reciente disponible

    // Otras dependencias...
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}