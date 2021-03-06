package com.example.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.MovieData
import com.example.data.MovieDetailData
import com.example.db.dao.MovieDAO
import com.example.db.dao.MovieDetailDAO

@Database(entities = [MovieData::class, MovieDetailData::class], version = 3, exportSchema = false)
@TypeConverters(MovieDetailData.MovieDetailTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
    abstract fun getMovieDetailDAO(): MovieDetailDAO

    companion object {
        private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        MovieDatabase::class.java.name
                    ).allowMainThreadQueries().build()
                }
                return instance as MovieDatabase
            }
        }
    }
}