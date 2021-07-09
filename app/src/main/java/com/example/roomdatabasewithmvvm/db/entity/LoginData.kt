package com.example.roomdatabasewithmvvm.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "login_table")
data class LoginData(

    var first_name: String,

    var last_name: String,

    var email: String,

    var password: String,

    var cnf_password: String,
): Serializable  {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}