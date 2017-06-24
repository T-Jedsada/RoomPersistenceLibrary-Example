package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(StudentEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun getAppDatabase(context: Context): AppDatabase =
                Room.databaseBuilder(context, AppDatabase::class.java, "db_app").build()
    }

    abstract fun studentDao(): StudentDao
}
