package zut.mobappdev.irontrackpro.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zut.mobappdev.irontrackpro.BuildConfig
import zut.mobappdev.irontrackpro.data.remote.ExerciseApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        // Логер, щоб бачити запити в Logcat
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Інтерцептор, який автоматично чіпляє твій АПІ-ключ до ВСІХ запитів
        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-rapidapi-key", BuildConfig.EXERCISE_DB_API_KEY)
                .addHeader("x-rapidapi-host", "edb-with-videos-and-images-by-ascendapi.p.rapidapi.com")
                .build()
            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideExerciseApiService(client: OkHttpClient): ExerciseApiService {
        return Retrofit.Builder()
            .baseUrl("https://edb-with-videos-and-images-by-ascendapi.p.rapidapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExerciseApiService::class.java)
    }
}