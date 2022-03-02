plugins {
    id(Plugins.ANDROID_APPLICATION) version Plugins.Version.AGP apply false
    id(Plugins.ANDROID_LIBRARY) version Plugins.Version.AGP apply false
    id(Plugins.KOTLIN_ANDROID) version Plugins.Version.KOTLIN apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}