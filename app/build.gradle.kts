plugins {
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.android.application)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.kentvu.csproblems"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  defaultConfig {
    applicationId = "com.kentvu.csproblems"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 2
    versionName = "2.0"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }

  //buildFeatures { compose = true }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = libs.versions.jvmTarget.get()
  }
}

dependencies {
  implementation(project(":ui"))
  //implementation(libs.androidx.appcompat)
  implementation(libs.androidx.core)
  implementation(libs.androidx.activity.activityCompose)
  //implementation "androidx.constraintlayout:constraintlayout:1.1.3"
  //implementation "com.google.android.material:material:1.0.0"

  testImplementation(libs.junit)
  //annotationProcessor "com.google.dagger:dagger-compiler:2.x"
}
