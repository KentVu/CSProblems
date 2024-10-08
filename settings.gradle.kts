//@file:Suppress("UnstableApiUsage")

pluginManagement {
  repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "CSProblems"
include(":model")
include(":ui")
include(":app")
include(":desktop")
