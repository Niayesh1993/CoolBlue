package xyz.zohre.coolblue.di

import com.example.coolblue.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.zohre.data.discovery.ProductDataSource


@InstallIn(SingletonComponent::class)
@Module
class ProductApiModule {

    @Provides
    fun provideRetrofitService(): ProductDataSource {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(interceptor)
        }
        val client = builder.build()
        return Retrofit.Builder()
            .baseUrl("https://bdk0sta2n0.execute-api.eu-west-1.amazonaws.com")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(client)
            .build()
            .create(ProductDataSource::class.java)
    }
}