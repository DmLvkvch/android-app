package com.example.ricknmortyapp.model.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ricknmortyapp.model.entity.character.Character

@Database(version = 1, entities = [Character::class, RemoteKeys::class], exportSchema = false)
@TypeConverters(com.example.ricknmortyapp.model.repository.local.TypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getCharacterDao(): CharacterDao

    companion object {

        val DOGGO_DB = "ram.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DOGGO_DB)
                .build()
    }

}