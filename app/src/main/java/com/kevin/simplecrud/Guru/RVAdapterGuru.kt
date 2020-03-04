package com.kevin.simplecrud.Guru

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.simplecrud.R
import kotlinx.android.synthetic.main.guru_list.view.*

class RVAdapterGuru(private val context: Context, private val arrayList: ArrayList<Gurus>) :
    RecyclerView.Adapter<RVAdapterGuru.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.guru_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbNipList.text = arrayList.get(position).nip
        holder.view.lbNamaGuruList.text = "Nama Guru : " + arrayList.get(position).nama_guru
        holder.view.lbAlamatGuruList.text = "Alamat : " + arrayList.get(position).alamat_guru
        holder.view.lbNoTelpGuruList.text = "Nomor Telepon : " + arrayList.get(position).no_telp_guru
        holder.view.lbGenderList.text = "Jenis kelamin : " + arrayList.get(position).gender
        holder.view.lbMataPelajaranList.text = "Mata pelajaran : " + arrayList.get(position).mata_pelajaran
        holder.view.lbHobiList.text = "Hobby : " + arrayList.get(position).hobi
        holder.view.cvList.setOnClickListener {
            val i = Intent(context, ManageGuruActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("editmode", "1")
            i.putExtra("nip", arrayList.get(position).nip)
            i.putExtra("nama_guru", arrayList.get(position).nama_guru)
            i.putExtra("alamat_guru", arrayList.get(position).alamat_guru)
            i.putExtra("no_telp_guru", arrayList.get(position).no_telp_guru)
            i.putExtra("gender", arrayList.get(position).gender)
            i.putExtra("mata_pelajaran", arrayList.get(position).mata_pelajaran)
            i.putExtra("hobi", arrayList.get(position).hobi)
            context.startActivity(i)
        }

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}