package com.example.roomdatabasewithmvvm.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.roomdatabasewithmvvm.db.dao.LoginDao
import com.example.roomdatabasewithmvvm.db.entity.LoginData


@Database(entities = [LoginData::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao


    companion object {
        private var instance: LoginDatabase? = null

        fun getInstance(context: Context): LoginDatabase {
            if (instance == null) {
                synchronized(LoginDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                PopulateDbAsyncTask(instance)
//                    .execute()
            }
        }

    }
    class PopulateDbAsyncTask(db: LoginDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.loginDao()

        override fun doInBackground(vararg p0: Unit?) {
            noteDao!!.insert(LoginData("Title 1", "description 1","","",""))
            noteDao.insert(LoginData("Title 2", "description 2","","",""))
            noteDao.insert(LoginData("Title 3", "description 3","","",""))
        }
    }

}