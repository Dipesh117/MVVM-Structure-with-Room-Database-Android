package com.example.roomdatabasewithmvvm.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.roomdatabasewithmvvm.db.dao.LoginDao
import com.example.roomdatabasewithmvvm.db.entity.LoginData

class LoginRepository(private val loginDao: LoginDao) {

    private val allData: LiveData<List<LoginData>> = loginDao.getAllData()

    fun insert(note: LoginData) {
        InsertNoteAsyncTask(
            loginDao
        ).execute(note)
    }

    fun deleteAllData() {
        DeleteAllNotesAsyncTask(
            loginDao
        ).execute()
    }

    fun deleteData(logindata:LoginData) {
        DeleteDataAsyncTask(
            loginDao
        ).execute(logindata)
    }

    fun updateData(updatedata:LoginData) {
        UpdateDataAsyncTask(
            loginDao
        ).execute(updatedata)
    }


    fun getAllData(): LiveData<List<LoginData>> {
        return allData
    }

    private class InsertNoteAsyncTask(val loginDao: LoginDao) : AsyncTask<LoginData, Unit, Unit>() {

        override fun doInBackground(vararg note: LoginData?) {
            loginDao.insert(note[0]!!)
        }
    }


    private class DeleteAllNotesAsyncTask(val loginDao: LoginDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            loginDao.deleteAllData()
        }
    }

    private class DeleteDataAsyncTask(val loginDao: LoginDao) : AsyncTask<LoginData, Unit, Unit>() {

        override fun doInBackground(vararg login: LoginData?) {
            loginDao.deleteData(login[0]!!)
        }
    }

    private class UpdateDataAsyncTask(val loginDao: LoginDao) : AsyncTask<LoginData, Unit, Unit>() {

        override fun doInBackground(vararg update: LoginData?) {
            loginDao.updateData(update[0]!!)
        }
    }

}