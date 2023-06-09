plugins {
    id(Plugins.Java.library)
    id(Plugins.Kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies{

    //coroutine
    implementation(Deps.Coroutines.core)
    //inject
    implementation(Deps.JavaX.inject)
}