package com.example.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "MovieDetail")
data class MovieDetailData(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int=0,
    val author: String,
    @Ignore
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val updated_at: String,
    val url: String
) {
    data class AuthorDetails(
        val name: String="",
        val username: String="",
        val avatar_path: String = "",
        val rating: Int=0
    )
}