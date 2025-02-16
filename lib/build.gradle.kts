plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("multiplatform") version "2.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvm()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib-common")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

detekt {
    autoCorrect = true
    buildUponDefaultConfig = true
    autoCorrect = true

    source = files("src/commonMain/kotlin", "src/commonTest/kotlin")
}
