package com.example.roomdatabasewithmvvm.adapter


import android.icu.text.Transliterator
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasewithmvvm.R
import com.example.roomdatabasewithmvvm.db.entity.LoginData

class LoginAdapter(listener: OnItemClickListener?) : RecyclerView.Adapter<LoginAdapter.LoginHolder>() {
    private var login_data: List<LoginData> = ArrayList()
    private var listener: OnItemClickListener = listener!!


    interface OnItemClickListener {
        fun onItemClick(position:View?,loginData:LoginData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.login_item, parent, false)
        return LoginHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: LoginHolder, position: Int) {
        val currentNote = login_data[position]
        holder.text_fname.text = currentNote.first_name
        holder.text_lname.text = currentNote.last_name
        holder.text_email.text = currentNote.email
        holder.img_edit.setOnClickListener(View.OnClickListener {
            listener.onItemClick(holder.img_edit,login_data[position])
        })

        holder.img_delete.setOnClickListener(View.OnClickListener {
            listener.onItemClick(holder.img_delete,login_data[position])
        })


    }

    override fun getItemCount(): Int {
        return login_data.size
    }

    fun setNotes(login_data: List<LoginData>) {
        this.login_data = login_data
        notifyDataSetChanged()
    }

    inner class LoginHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_fname: TextView = itemView.findViewById(R.id.text_fname)
        var text_lname: TextView = itemView.findViewById(R.id.text_lname)
        var text_email: TextView = itemView.findViewById(R.id.text_email)
        var img_edit: ImageView = itemView.findViewById(R.id.img_edit)
        var img_delete: ImageView = itemView.findViewById(R.id.img_delete)

    }




}
