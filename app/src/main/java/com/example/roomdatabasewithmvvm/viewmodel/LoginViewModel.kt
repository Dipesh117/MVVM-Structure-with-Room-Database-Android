package com.example.roomdatabasewithmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomdatabasewithmvvm.repository.LoginRepository
import com.example.roomdatabasewithmvvm.db.entity.LoginData

class LoginViewModel(private var repository: LoginRepository): ViewModel() {

    private var allData: LiveData<List<LoginData>> = repository.getAllData()

    fun insert(logindata: LoginData) {
        repository.insert(logindata)
    }

    fun deleteAllData() {
        repository.deleteAllData()
    }

    fun deleteData(logindata: LoginData) {
        repository.deleteData(logindata)
    }

    fun updateData(updateData: LoginData) {
        repository.updateData(updateData)
    }

    fun getAllData(): LiveData<List<LoginData>> {
        return allData
    }
}