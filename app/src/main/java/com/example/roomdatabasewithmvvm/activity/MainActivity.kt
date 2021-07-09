package com.example.roomdatabasewithmvvm.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasewithmvvm.R
import com.example.roomdatabasewithmvvm.adapter.LoginAdapter
import com.example.roomdatabasewithmvvm.adapter.LoginAdapter.OnItemClickListener
import com.example.roomdatabasewithmvvm.db.entity.LoginData
import com.example.roomdatabasewithmvvm.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val noteViewModel: LoginViewModel by inject()
    private lateinit var adapter: LoginAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupButtonAddNote()
        setupRecyclerView()

        noteViewModel.getAllData().observe(this,
            Observer<List<LoginData>> { list ->
                list?.let {
                    adapter.setNotes(it)
                }
            })
    }



    private fun setupButtonAddNote() {
        buttonAddData.setOnClickListener {
            startActivityForResult(
                Intent(this, LoginActivity::class.java).putExtra("type",1),
                ADD_NOTE_REQUEST
            )
        }
    }


    private fun setupRecyclerView() {
        adapter = LoginAdapter(myClickListner())
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    fun myClickListner (): OnItemClickListener =
        object : OnItemClickListener {
            override fun onItemClick(position: View?,loginData: LoginData) {
                Toast.makeText(this@MainActivity, "" + position, Toast.LENGTH_SHORT).show()
                when (position!!.id){
                    R.id.img_delete -> {
                        noteViewModel.deleteData(loginData)
                    }

                    R.id.img_edit -> {
                        val intent = Intent(this@MainActivity,LoginActivity::class.java)
                        intent.putExtra("loginData",loginData)
                        intent.putExtra("type",0)
                        startActivity(intent)
                    }
                }

            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newNote = LoginData(
                data.getStringExtra(LoginActivity.FIRST_NAME)!!,
                data.getStringExtra(LoginActivity.LAST_NAME)!!,
                data.getStringExtra(LoginActivity.EMAIL)!!,
                data.getStringExtra(LoginActivity.PASSWORD)!!,
                data.getStringExtra(LoginActivity.CONFIRM_PASSWORD)!!
            )

            noteViewModel.insert(newNote)

            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        private const val ADD_NOTE_REQUEST = 1
    }

}