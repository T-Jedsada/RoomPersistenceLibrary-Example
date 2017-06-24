package pondthaitay.roompersistencelibrary.example

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context


@Database(entities = arrayOf(StudentEntity::class), version = 16)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private val MIGRATION_15_16: Migration = object : Migration(15, 16) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student "
                        + " ADD COLUMN age INTEGER")
            }
        }

        fun getAppDatabase(context: Context): AppDatabase =
                Room.databaseBuilder(context, AppDatabase::class.java, "db_app")
                        .addMigrations(MIGRATION_15_16).build()
    }

    abstract fun studentDao(): StudentDao
}