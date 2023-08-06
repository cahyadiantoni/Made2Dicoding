package id.cahya.core.di

import id.cahya.core.BuildConfig
import id.cahya.core.data.TmdbRepository
import id.cahya.core.data.source.local.LocalDataSource
import id.cahya.core.data.source.local.room.TmdbDatabase
import id.cahya.core.data.source.remote.RemoteDataSource
import id.cahya.core.data.source.remote.network.ApiService
import id.cahya.core.domain.repository.ITmdbRepository
import id.cahya.core.util.AppExecutors
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single { TmdbDatabase.getInstance(androidContext()) }
    factory { get<TmdbDatabase>().tmdbDao() }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NPIMWkzcNG/MyZsVExrC6tdy5LTZzeeKg2UlnGG55UY=")
            .build()
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource.getInstance(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITmdbRepository> {
        TmdbRepository.getInstance(
            get(),
            get(),
            get()
        )
    }
}