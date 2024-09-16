plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.android.library)
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.compose.compiler)
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
    iosSimulatorArm64()
  )
    .takeIf { rootProject.extra["enableIos"] == true } // Export the framework only for Xcode builds
    ?.forEach {
      // This `shared` framework is exported for app-ios-compose
      it.binaries.framework {
        baseName = "composeUi" // Used in app-ios-compose
        //https://github.com/cashapp/sqldelight/issues/1442
        //this is the only way that works: https://github.com/touchlab/KaMPKit/blob/main/shared%2Fbuild.gradle.kts#L61-L61
        //linkerOpts("-lsqlite3")

        export(project(":model"))
        //export(project(":shared-data"))
        export(libs.decompose.decompose)
        export(libs.essenty.lifecycle)
      }
    }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(project(":model"))

        // Compose Libraries
        implementation(compose.ui)
        implementation(compose.foundation)
        implementation(compose.material3)
        //implementation(compose.materialIconsExtended)
        implementation(compose.components.resources)

        // Decompose Libraries
        implementation(libs.decompose.extensionsComposeJetbrains)

        implementation(libs.richeditor)
      }
    }
  }
}

compose.resources {
  packageOfResClass = "com.kentvu.csproblems.ui"
  generateResClass = always
  //publicResClass = true
}

android {
  namespace = "com.kentvu.csproblems.ui"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()
  }

  buildFeatures { compose = true }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}




