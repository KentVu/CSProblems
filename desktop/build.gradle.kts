import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.compose.multiplatform)
  alias(libs.plugins.compose.compiler)
}

kotlin {
  jvm {
    withJava()
  }

  sourceSets {
    val jvmMain by getting {
      dependencies {
        implementation(project(":ui"))

        //implementation(compose.ui)
        implementation(compose.desktop.currentOs)
        implementation(libs.decompose.extensionsComposeJetbrains)
        implementation(libs.kotlinx.coroutines.swing)
      }
    }
  }
}

compose.desktop {
  application {
    mainClass = "com.kentvu.csproblems.MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "CSProblems"
      packageVersion = libs.versions.project.get()
    }
  }
}
