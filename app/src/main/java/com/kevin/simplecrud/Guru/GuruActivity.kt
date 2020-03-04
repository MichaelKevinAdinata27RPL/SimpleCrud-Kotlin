package com.kevin.simplecrud.Guru

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
import kotlinx.android.synthetic.main.activity_guru.*
import org.json.JSONObject

class GuruActivity : AppCompatActivity() {

    var arrayList = ArrayList<Gurus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guru)

        supportActionBar?.title = "Data Guru"

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mFloatingActionButton.setOnClickListener {
            startActivity(Intent(this, ManageGuruActivity::class.java))
        }

    }

    private fun loadAllStudents() {
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()
        AndroidNetworking.get(ApiEndPointGuru.READ)
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
                            "Guru data is empty, Add the data first",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    for (i in 0 until jsonArray?.length()!!) {

                        val jsonObject = jsonArray.optJSONObject(i)
                        arrayList.add(
                            Gurus(
                                jsonObject.getString("nip"),
                                jsonObject.getString("nama_guru"),
                                jsonObject.getString("alamat_guru"),
                                jsonObject.getString("no_telp_guru"),
                                jsonObject.getString("gender"),
                                jsonObject.getString("mata_pelajaran"),
                                jsonObject.getString("hobi")
                            )
                        )

                        if (jsonArray.length() - 1 == i) {

                            loading.dismiss()
                            val adapter = RVAdapterGuru(
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
        loadAllStudents()
    }
}
