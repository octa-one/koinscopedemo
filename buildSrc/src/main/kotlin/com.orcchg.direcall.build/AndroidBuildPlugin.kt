package com.orcchg.direcall.build

import org.gradle.api.Plugin
import org.gradle.api.Project

open class AndroidBuildPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.configureAndroid()
    }
}

internal fun Project.configureAndroid() {}// = this.extensions.getByType<AndroidBaseExtension>().run {
//    compileSdkVersion(28)
//    defaultConfig {
//        minSdkVersion(21)
//        targetSdkVersion(28)
//        versionCode = 2
//        versionName = "1.0.1"
//        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
//        }
//
//        getByName("debug") {
//            isTestCoverageEnabled = true
//        }
//    }
//
//    packagingOptions {
//        exclude("META-INF/NOTICE.txt")
//        // ...
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//}
