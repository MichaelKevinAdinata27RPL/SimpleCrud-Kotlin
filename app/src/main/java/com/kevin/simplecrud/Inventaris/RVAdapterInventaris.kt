package com.kevin.simplecrud.Inventaris

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.simplecrud.R
import kotlinx.android.synthetic.main.inventaris_list.view.*

class RVAdapterInventaris(private val context: Context, private val arrayList: ArrayList<Inventaris>) :
    RecyclerView.Adapter<RVAdapterInventaris.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.inventaris_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.lbIdBarangList.text = arrayList.get(position).id_barang
        holder.view.lbNamaBarangList.text = "Nama Barang : " + arrayList.get(position).nama_barang
        holder.view.lbJenisBarangList.text = "Jenis Barang : " + arrayList.get(position).jenis_barang
        holder.view.lbJumlahBarangList.text = "Jumlah Barang : " + arrayList.get(position).jumlah_barang
        holder.view.cvList.setOnClickListener {
            val i = Intent(context, ManageInventarisActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("editmode", "1")
            i.putExtra("id_barang", arrayList.get(position).id_barang)
            i.putExtra("nama_barang", arrayList.get(position).nama_barang)
            i.putExtra("jenis_barang", arrayList.get(position).jenis_barang)
            i.putExtra("jumlah_barang", arrayList.get(position).jumlah_barang)
            context.startActivity(i)
        }

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
}