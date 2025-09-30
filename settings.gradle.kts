pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FelixMandyme-JuniorAssessment"
include(":app")
include(":core:common")
include(":feature:todo:data")
include(":feature:todo:domain")
include(":feature:todo:ui")
include(":feature:weather:data")
include(":feature:weather:domain")
include(":feature:weather:ui")
