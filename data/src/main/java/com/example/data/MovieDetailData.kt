package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type


@Entity(tableName = "MovieDetail")
data class MovieDetailData(
    var id: String = "",
    var movieId: Long = 0,
    var author: String = "",
    var author_details: AuthorDetails?,
    var content: String = "",
    var created_at: String = "",
    var updated_at: String = "",
    var url: String = ""
)
    : Serializable
{


    @PrimaryKey(autoGenerate = true)
    var localId: Long = 0L

    data class AuthorDetails(
        var name: String = "",
        var username: String = "",
        var avatar_path: String = "",
        var rating: Int = 0
    ) : Serializable

    object MovieDetailTypeConverter : Serializable {
        @TypeConverter
        @JvmStatic
        fun fromMovieDetailList(movieDetail: AuthorDetails?): String? {
            if (movieDetail == null) {
                return null
            }
            val gSon = Gson()
            val type: Type =
                object : TypeToken<AuthorDetails?>() {}.type
            return gSon.toJson(movieDetail, type)
        }

        @TypeConverter
        @JvmStatic
        fun toOptionValuesList(movieDetailString: String?): AuthorDetails? {
            if (movieDetailString == null) {
                return null
            }
            val gSon = Gson()
            val type =
                object : TypeToken<AuthorDetails?>() {}.type
            return gSon.fromJson<AuthorDetails>(movieDetailString, type)
        }

    }

}