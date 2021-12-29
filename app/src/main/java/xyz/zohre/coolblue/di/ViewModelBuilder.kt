package xyz.zohre.coolblue.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap
import xyz.zohre.ui.HomeViewModel

/**
 * Maybe it's better to use viewmodel injector from jetpack but because it was in alpha stage I wrote something by
 * myself and we can change it later if we know the SDK is stable.
 *
 * Also I Know FragmentComponent is not optimized for the factory but because it need map for creating viewmodels,
 * we need to put them in same module
 */
@Module
@InstallIn(FragmentComponent::class)
interface ViewModelBuilder {
    @Binds
    fun bindFactory(factory: CoolBlueViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}
