package io.arkstud.wordbox_roberto.di

import io.arkstud.wordbox_roberto.ui.person_list.PersonListViewModel
import io.arkstud.wordbox_roberto.ui.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    /* SPLASH */
    viewModel { SplashViewModel() }
    /* MAIN */
    viewModel { PersonListViewModel(get()) }

}
