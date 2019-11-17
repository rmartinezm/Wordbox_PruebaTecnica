
object Versions {
    /* ROOT */
    const val KOTLIN: String = "1.3.50"
    /* KOIN */
    const val KOIN: String = "1.0.2"
    /* RETROFIT */
    const val RETROFIT: String = "2.5.0"
    const val RETROFIT_GSON: String = "2.4.0"
    const val RETROFIT_COROUTINES: String = "0.9.2"
    const val RETROFIT_LOGGING_INTERCEPTOR: String = "3.12.1"
    /* COROUTINES */
    const val COROUTINES: String = "1.2.0"
    /* GLIDE */
    const val GLIDE: String = "4.9.0"
}

object Dependencies {

    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"

    /* KOIN */
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.KOIN}"

    /* RETROFIT*/
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_GSON}"
    // COROUTINES
    const val retrofitCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.RETROFIT_COROUTINES}"
    // INTERCEPTORS
    const val retrofitLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.RETROFIT_LOGGING_INTERCEPTOR}"

    /* COROUTINES */
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    /* GLIDE */
    const val glide = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"

}