package com.example.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.MovieData
import com.example.db.dao.MovieDAO

@Database(entities = [MovieData::class] , version=1)
abstract class MovieDatabase :RoomDatabase() {
    abstract fun getMovieDAO() : MovieDAO
    companion object{
        private var  instance: MovieDatabase?=null
        fun getInstance(context : Context): MovieDatabase{
            synchronized(this){
                if (instance==null){
                    instance= Room.databaseBuilder(
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