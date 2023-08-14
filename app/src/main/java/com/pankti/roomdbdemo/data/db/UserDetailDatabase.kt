package com.pankti.roomdbdemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pankti.roomdbdemo.domain.entities.UserDataModel

@Database(entities = [UserDataModel::class], version = 2, exportSchema = false)
abstract class UserDetailDatabase : RoomDatabase() {

    abstract fun  userDetail():UserDetailDao

    companion object {
        @Volatile
        private var instance: UserDetailDatabase? = null

        fun getDatabase(context: Context): UserDetailDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(context.applicationContext, UserDetailDatabase::class.java,
                    "user-detail-database")
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build()
                instance = newInstance

                return newInstance
            }
        }

        // Migration from version 1 to version 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user_detail ADD COLUMN emailId TEXT NOT NULL DEFAULT ''")
            }
        }
    }


}