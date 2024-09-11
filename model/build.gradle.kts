plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.serialization)
}

kotlin {
  applyDefaultHierarchyTemplate()

  jvm()

  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
      }
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
  )
    .takeIf { "XCODE_VERSION_MAJOR" in System.getenv().keys } // Export the framework only for Xcode builds
    ?.forEach {
      // This `shared` framework is exported for app-ios-swift
      it.binaries.framework {
        baseName = "model" // Used in app-ios-swift
      }
    }

  sourceSets {
    val commonMain by getting {
      dependencies {
        //implementation fileTree(dir: 'libs', include: ['*.jar'])
        implementation(libs.kotlinx.serialization)
        //implementation "org.jetbrains.kotlin:kotlin-reflect:$kotlin_version"
        implementation(libs.kaml)
        api(libs.kotlinx.coroutines)
        api(libs.touchlab.kermit)
        api(libs.decompose.decompose)
        api(libs.essenty.lifecycle)
      }
    }
  }
}

android {
  namespace = "com.kentvu.csproblems.model"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

dependencies {
  implementation(kotlin("reflect"))
  testImplementation(libs.junit)
}
