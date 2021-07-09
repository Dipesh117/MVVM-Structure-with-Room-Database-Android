package com.example.roomdatabasewithmvvm.di

import android.view.View
import com.example.roomdatabasewithmvvm.adapter.LoginAdapter
import com.example.roomdatabasewithmvvm.db.LoginDatabase
import com.example.roomdatabasewithmvvm.db.entity.LoginData
import com.example.roomdatabasewithmvvm.repository.LoginRepository
import com.example.roomdatabasewithmvvm.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { LoginDatabase.getInstance(
        context = get()
    )}
    factory { get<LoginDatabase>().loginDao() }
}

val repositoryModule = module {
    single { LoginRepository(get()) }
}

val uiModule = module {
    factory { LoginAdapter(myClickListner()) }
    viewModel { LoginViewModel(get())}
}

fun myClickListner (): LoginAdapter.OnItemClickListener =
    object : LoginAdapter.OnItemClickListener {
        override fun onItemClick(position: View?,loginData: LoginData) {

        }
    }

