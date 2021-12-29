package xyz.zohre.coolblue.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.zohre.data.discovery.ProductRepository
import xyz.zohre.data.discovery.ProductRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
class DataModules {

    @Provides
    fun provideProductRepository(venuesRepositoryImpl: ProductRepositoryImpl): ProductRepository {
        return venuesRepositoryImpl
    }
}
