object Test {

    private object Version {
        const val JUNIT = "4.13.2"
        const val JUNIT_EXT = "1.1.3"
        const val ESPRESSO = "3.4.0"
        const val COMPOSE_UI_TEST = "1.0.5"
    }

    const val JUNIT = "junit:junit:${Version.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Version.JUNIT_EXT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    const val COMPOSE_UI_TEST = "androidx.compose.ui:ui-test-junit4:${Version.COMPOSE_UI_TEST}"
}
