package com.example.roomdatabasewithmvvm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabasewithmvvm.db.entity.LoginData


@Dao
interface LoginDao {

    @Insert
    fun insert(login: LoginData)

    @Query("DELETE FROM login_table")
    fun deleteAllData()

    @Query("SELECT * FROM login_table ")
    fun getAllData(): LiveData<List<LoginData>>

    @Delete
    fun deleteData(login: LoginData)

    @Update
    fun updateData(vararg login: LoginData)

}