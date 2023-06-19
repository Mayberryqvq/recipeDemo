package com.mayberry.recipedemo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mayberry.recipedemo.util.Converter

@TypeConverters(Converter::class)
@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

    //单例创建数据库
    companion object {
        private var INSTANCE:RecipeDatabase? = null
        fun getInstance(context: Context): RecipeDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_db").build()
                }
                return INSTANCE!!
            }
        }
    }
}