package xyz.zohre.coolblue.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import xyz.zohre.presentation.AppNavigator

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {
    @Provides
    fun provideAppNavigator(coolBlueAppNavigator: CoolBlueAppNavigator): AppNavigator {
        return coolBlueAppNavigator
    }

    @Provides
    fun provideLocationClient(@ActivityContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}