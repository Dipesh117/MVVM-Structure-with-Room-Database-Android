package com.example.roomdatabasewithmvvm.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabasewithmvvm.R
import com.example.roomdatabasewithmvvm.db.entity.LoginData
import com.example.roomdatabasewithmvvm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() , View.OnClickListener {


    private val noteViewModel: LoginViewModel by inject()
    private var type:Int  = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }
    var loginData:LoginData?=null
    fun initView(){
        btn_add_data.setOnClickListener(this)
        type = intent.getIntExtra("type",0);
        val i = intent
         loginData = i.getSerializableExtra("loginData") as? LoginData
            if (type == 0){
                txt_fname.setText(loginData!!.first_name)
                txt_lname.setText(loginData!!.last_name)
                txt_email.setText(loginData!!.email)
                txt_password.setText(loginData!!.password)
                txt_cpassword.setText(loginData!!.cnf_password)
                btn_add_data.setText("Update Data")
            } else {
                btn_add_data.setText("Add Data")
            }
    }
    override fun onClick(v: View?) {
       when (v!!.id){
        R.id.btn_add_data -> {
            if (type == 0){
                updateData()
            } else {
                saveNote()
            }

        }

       }
    }

    private fun saveNote() {
        if (txt_fname.text.toString().trim().isBlank() || txt_lname.text.toString().trim().isBlank() || txt_email.text.toString().trim().isBlank() || txt_password.text.toString().trim().isBlank() || txt_cpassword.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        } else if (!txt_cpassword.text.toString().equals(txt_password.text.toString())){
            Toast.makeText(this, "Confirm password not matched with password", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(FIRST_NAME, txt_fname.text.toString())
            putExtra(LAST_NAME, txt_lname.text.toString())
            putExtra(EMAIL, txt_email.text.toString())
            putExtra(PASSWORD, txt_password.text.toString())
            putExtra(CONFIRM_PASSWORD, txt_cpassword.text.toString())
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }

    private fun updateData() {
        if (txt_fname.text.toString().trim().isBlank() || txt_lname.text.toString().trim().isBlank() || txt_email.text.toString().trim().isBlank() || txt_password.text.toString().trim().isBlank() || txt_cpassword.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        } else if (!txt_cpassword.text.toString().equals(txt_password.text.toString())){
            Toast.makeText(this, "Confirm password not matched with password", Toast.LENGTH_SHORT).show()
            return
        }
        val logindata2 = LoginData(txt_fname.text.toString(),txt_lname.text.toString(),txt_email.text.toString(),txt_password.text.toString(),txt_cpassword.text.toString())
        logindata2.id= loginData?.id!!
        noteViewModel.updateData(logindata2 )
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val FIRST_NAME = "FIRST_NAME"
        const val LAST_NAME = "LAST_NAME"
        const val EMAIL = "EMAIL"
        const val PASSWORD = "PASSWORD"
        const val CONFIRM_PASSWORD = "CONFIRM_PASSWORD"
    }
}