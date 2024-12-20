plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.siprocal.sdktest"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.jrcarcamod"
                version = "1.0.0"
                artifactId = "chronos"
                pom {
                    name.set("Librería Chronos")
                    description.set("Descripción de la variante Chronos.")
                    url.set("https://github.com/jrcarcamod/testlibrearymaven")
                    licenses {
                        license {
                            name.set("The Apache Software License, Version 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("jrcarcamod")
                            name.set("Jose Carcamo")
                            email.set("jose.carcamo@siprocal.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/jrcarcamod/testlibrearymaven.git")
                        developerConnection.set("scm:git:ssh://git@github.com/jrcarcamod/testlibrearymaven.git")
                        url.set("https://github.com/jrcarcamod/testlibrearymaven")
                    }
                }
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Digita1Reef/phoenix")
                credentials {
                    username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME_GITHUB")
                    password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN_GITHUB")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}