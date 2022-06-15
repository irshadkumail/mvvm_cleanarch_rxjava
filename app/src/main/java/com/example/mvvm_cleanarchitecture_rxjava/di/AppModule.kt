package com.example.mvvm_cleanarchitecture_rxjava.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.AppDatabase
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.AlbumDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.PhotoDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.UserDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import com.example.mvvm_cleanarchitecture_rxjava.data.repository.AlbumRepositoryImpl
import com.example.mvvm_cleanarchitecture_rxjava.data.repository.PhotoRepositoryImpl
import com.example.mvvm_cleanarchitecture_rxjava.data.repository.UserRepositoryImpl
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.AlbumRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.PhotoRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "sample_app.db")
            .build()
    }

    @Provides
    @Singleton
    fun getAlbumDao(appDatabase: AppDatabase): AlbumDao {
        return appDatabase.albumDao()
    }

    @Provides
    @Singleton
    fun getPhotoDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao()
    }

    @Provides
    @Singleton
    fun getUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                /* If there is Internet, get the cache that was stored 5 seconds ago.
                 * If the cache is older than 5 seconds, then discard it,
                 * and indicate an error in fetching the response.
                 * The 'max-age' attribute is responsible for this behavior.
                 */
                request =
                    if (true) request.newBuilder() // make default to true till i figure out how to inject network status
                        .header("Cache-Control", "public, max-age=" + 5).build()
                    /*If there is no Internet, get the cache that was stored 7 days ago.
                     * If the cache is older than 7 days, then discard it,
                     * and indicate an error in fetching the response.
                     * The 'max-stale' attribute is responsible for this behavior.
                     * The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                     */
                    else request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
        return client.build()
    }

    @Provides
    @Singleton
    public fun getAlbumRepo(apiService: ApiService, dao: AlbumDao): AlbumRepository {
        return AlbumRepositoryImpl(apiService, dao)
    }

    @Provides
    @Singleton
    public fun getPhotoRepo(apiService: ApiService, dao: PhotoDao): PhotoRepository {
        return PhotoRepositoryImpl(apiService, dao)
    }

    @Provides
    @Singleton
    public fun getUserRepo(api: ApiService, dao: UserDao): UserRepository {
        return UserRepositoryImpl(api, dao)
    }


}