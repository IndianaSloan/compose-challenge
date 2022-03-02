object AndroidX {

    object Compose {

        @Suppress("MemberVisibilityCanBePrivate")
        const val VERSION = "1.1.0"
        private const val ACTIVITY_VERSION = "1.4.0"
        private const val CONSTRAINT_VERSION = "1.0.0"

        const val UI = "androidx.compose.ui:ui:$VERSION"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:$VERSION"
        const val ACTIVITY = "androidx.activity:activity-compose:$ACTIVITY_VERSION"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout-compose:$CONSTRAINT_VERSION"

        const val MATERIAL = "androidx.compose.material:material:$VERSION"
    }


    private object Version {
        const val CORE_KTX = "1.7.0"
        const val LIFECYCLE_KTX = "2.4.0"
    }

    const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
    const val LIFECYCLE_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LIFECYCLE_KTX}"
}
