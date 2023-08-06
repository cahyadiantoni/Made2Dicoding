package id.cahya.core.data.source.local.room

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.cahya.core.data.source.local.entity.MovieEntity
import id.cahya.core.data.source.local.entity.SeasonEntity
import id.cahya.core.data.source.local.entity.TvShowEntity
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, SeasonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TmdbDatabase : RoomDatabase() {
    abstract fun tmdbDao(): TmdbDao

    companion object {

        @Volatile
        private var INSTANCE: TmdbDatabase? = null

        fun getInstance(context: Context): TmdbDatabase =
            INSTANCE ?: synchronized(this) {
                val passphrase: ByteArray = SQLiteDatabase.getBytes("cahya".toCharArray())
                val factory = SupportFactory(passphrase)
                Room.databaseBuilder(
                    context.applicationContext,
                    TmdbDatabase::class.java,
                    "Tmdb.db"
                )
                    .fallbackToDestructiveMigration()
                    .openHelperFactory(factory)
                    .build()
                    .apply {
                        INSTANCE = this
                    }
            }
    }
}