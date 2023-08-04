package id.cahya.made1dicoding

import android.app.Application
import id.cahya.core.di.databaseModule
import id.cahya.core.di.networkModule
import id.cahya.core.di.repositoryModule
import id.cahya.made1dicoding.di.useCaseModule
import id.cahya.made1dicoding.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}