plugins {
    id(Plugins.AGP.application)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
    id(Plugins.DaggerHilt.hilt)
}

android {
    namespace = "com.example.architecnotes"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.example.architecnotes"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            proguardFiles (
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
    kapt {
        correctErrorTypes = true
    }

    viewBinding{
        android.buildFeatures.viewBinding=true
    }
}


dependencies {
    implementation(project(":notesData"))
    implementation(project(":notesDomain"))
    implementation(Deps.UI.androidCore)
    implementation(Deps.UI.appcompat)
    implementation(Deps.UI.material)
    implementation(Deps.UI.constraint)
    testImplementation(Deps.UI.espresso)
    androidTestImplementation(Deps.UI.extJunit)
    androidTestImplementation(Deps.UI.junit)
    implementation(Deps.UI.fragment)

    implementation(Deps.Room.roomRuntime)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.room)

    implementation(Deps.Coroutines.android)

    implementation(Deps.DaggerHilt.hilt)
    kapt(Deps.DaggerHilt.compiler)

    implementation(Deps.Lifecycle.viewmodel)
    implementation(Deps.Lifecycle.lifecycle)

    implementation(Deps.NavComponent.fragment)
    implementation(Deps.NavComponent.ui)

    implementation(platform(Deps.Bom.kotlinBom))

    implementation(Deps.ViewBindingDelegate.viewBindingDelegate)

}