package io.arkstud.wordbox_roberto.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val utilModule = module {
    single { androidApplication() }
}