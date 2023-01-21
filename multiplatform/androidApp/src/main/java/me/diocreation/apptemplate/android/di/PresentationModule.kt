package me.diocreation.apptemplate.android.di

import me.diocreation.apptemplate.android.ui.activity.MainViewModel
import me.diocreation.apptemplate.android.utils.LocaleManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { LocaleManager() }
    viewModel {
        MainViewModel(get())
    }
}