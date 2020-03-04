package com.kevin.simplecrud.Pegawai

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.simplecrud.R
import kotlinx.android.synthetic.main.pegawai_list.view.*

class RVAdapterPegawai(private val context: Context, private val arrayList: ArrayList<Pegawais>) :
    RecyclerView.Adapter<RVAdapterPegawai.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pegawai_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbNipList.text = arrayList.get(position).id
        holder.view.lbNama_pegawaiList.text = "Nama : " + arrayList.get(position).nama
        holder.view.lbAlamatList.text = "Alamat : " + arrayList.get(position).alamat
        holder.view.lbNo_telpList.text = "Telpon : " + arrayList.get(position).no_telp
        holder.view.cvListPegawai.setOnClickListener {
            val i = Intent(context, ManagePegawaiActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("editmode", "1")
            i.putExtra("id", arrayList.get(position).id)
            i.putExtra("nama", arrayList.get(position).nama)
            i.putExtra("alamat", arrayList.get(position).alamat)
            i.putExtra("no_telp", arrayList.get(position).no_telp)
            context.startActivity(i)
        }

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}