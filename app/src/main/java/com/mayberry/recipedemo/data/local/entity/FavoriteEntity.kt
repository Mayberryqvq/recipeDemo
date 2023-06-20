package com.mayberry.recipedemo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mayberry.recipedemo.data.model.Result

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val recipe: Result
    )