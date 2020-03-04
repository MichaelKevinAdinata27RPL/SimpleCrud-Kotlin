package com.kevin.simplecrud.User

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.simplecrud.R
import kotlinx.android.synthetic.main.user_list.view.*

class RVAdapterUser(private val context: Context, private val arrayList: ArrayList<Users>) :
    RecyclerView.Adapter<RVAdapterUser.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent,false)
        )
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbNimList.text = arrayList.get(position).id
        holder.view.lbNameList.text = "Nama : " + arrayList.get(position).nama
        holder.view.lbAddressList.text = "Alamat : " + arrayList.get(position).alamat
        holder.view.lbGenderList.text = "Jenis kelamin : " + arrayList.get(position).gender
        holder.view.cvList.setOnClickListener {
            val i = Intent(context, ManageUserActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("editmode", "1")
            i.putExtra("id", arrayList.get(position).id)
            i.putExtra("nama", arrayList.get(position).nama)
            i.putExtra("alamat", arrayList.get(position).alamat)
            i.putExtra("gender", arrayList.get(position).gender)
            context.startActivity(i)
        }

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}