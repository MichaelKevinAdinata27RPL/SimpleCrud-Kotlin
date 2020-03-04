package com.kevin.simplecrud.Pegawai

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.kevin.simplecrud.R
import kotlinx.android.synthetic.main.activity_user.*
import org.json.JSONObject

class PegawaiActivity : AppCompatActivity() {
    var arrayList = ArrayList<Pegawais>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pegawai)

        supportActionBar?.title = "Data Pegawai"

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mFloatingActionButton.setOnClickListener {
            startActivity(Intent(this, ManagePegawaiActivity::class.java))
        }

    }

    private fun loadAllPegawai() {
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()
        AndroidNetworking.get(ApiEndPointPegawai.READ)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()
                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray?.length() == 0) {
                        loading.dismiss()
                        Toast.makeText(
                            applicationContext,
                            "Student data is empty, Add the data first",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    for (i in 0 until jsonArray?.length()!!) {

                        val jsonObject = jsonArray.optJSONObject(i)
                        arrayList.add(
                            Pegawais(
                                jsonObject.getString("id"),
                                jsonObject.getString("nama"),
                                jsonObject.getString("alamat"),
                                jsonObject.getString("no_telp")
                            )
                        )

                        if (jsonArray.length() - 1 == i) {

                            loading.dismiss()
                            val adapter = RVAdapterPegawai(
                                applicationContext,
                                arrayList
                            )
                            adapter.notifyDataSetChanged()
                            mRecyclerView.adapter = adapter

                        }

                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        loadAllPegawai()
    }


}
