package com.kevin.simplecrud.Guru

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.kevin.simplecrud.R
import kotlinx.android.synthetic.main.activity_manage_guru.*
import org.json.JSONObject


class ManageGuruActivity : AppCompatActivity() {

    lateinit var i: Intent
    private var gender = "Pria"

    lateinit var spinner: Spinner
    var mataPelajaran: String? = ""

    private var hobi: String? = ""
    lateinit var cbBerenang: CheckBox
    lateinit var cbBasket: CheckBox
    lateinit var cbKuliner: CheckBox

    var daftar_mapel = arrayOf("Mat","IPA","Bindo","IPS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_guru)
        supportActionBar?.hide()
        i = intent

        if (i.hasExtra("editmode")) {

            if (i.getStringExtra("editmode").equals("1")) {

                onEditMode()

            }

        }

        spinner = findViewById(R.id.listMataPelajaran) as Spinner

        spinner.adapter = ArrayAdapter<CharSequence>(this,
            android.R.layout.simple_list_item_1, daftar_mapel)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                mataPelajaran = parent.getItemAtPosition(position).toString()
            }
        }

        cbBerenang = findViewById(R.id.cbBerenang) as CheckBox
        cbBasket = findViewById(R.id.cbBasket) as CheckBox
        cbKuliner = findViewById(R.id.cbKuliner) as CheckBox

        cbBerenang.setOnClickListener{
            if (cbBerenang.isChecked()) {
                hobi += " Berenang, "
            }
        }

        cbBasket.setOnClickListener{
            if (cbBasket.isChecked()) {
                hobi += " Basket, "
            }
        }

        cbKuliner.setOnClickListener{
            if (cbKuliner.isChecked()) {
                hobi += " Kuliner"
            }
        }

        rgGender.setOnCheckedChangeListener { radioGroup, i ->

            when (i) {

                R.id.radioLk -> {
                    gender = "Pria"
                }

                R.id.radioPr -> {
                    gender = "Wanita"
                }

            }

        }

        btnCreate.setOnClickListener {
            create()
        }

        btnUpdate.setOnClickListener {
            update()
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus data ini ?")
                .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }

    }



    private fun onEditMode() {

        txtNip.setText(i.getStringExtra("nip"))
        txtNamaGuru.setText(i.getStringExtra("nama_guru"))
        txtAlamatGuru.setText(i.getStringExtra("alamat_guru"))
        txtNoTelpGuru.setText(i.getStringExtra("no_telp_guru"))
        txtNip.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

        gender = i.getStringExtra("gender")
        mataPelajaran = i.getStringExtra("mata_pelajaran")
        hobi = ""

        if (gender.equals("Pria")) {
            rgGender.check(R.id.radioLk)
        } else {
            rgGender.check(R.id.radioPr)
        }

    }

    private fun create() {

        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPointGuru.CREATE)
            .addBodyParameter("nip", txtNip.text.toString())
            .addBodyParameter("nama_guru", txtNamaGuru.text.toString())
            .addBodyParameter("alamat_guru", txtAlamatGuru.text.toString())
            .addBodyParameter("no_telp_guru", txtNoTelpGuru.text.toString())
            .addBodyParameter("gender", gender)
            .addBodyParameter("mata_pelajaran", mataPelajaran)
            .addBodyParameter("hobi", hobi)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(
                        applicationContext,
                        response?.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()

                    if (response?.getString("message")?.contains("berhasil")!!) {
                        this@ManageGuruActivity.finish()
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

    private fun update() {

        val loading = ProgressDialog(this)
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPointGuru.UPDATE)
            .addBodyParameter("nip", txtNip.text.toString())
            .addBodyParameter("nama_guru", txtNamaGuru.text.toString())
            .addBodyParameter("alamat_guru", txtAlamatGuru.text.toString())
            .addBodyParameter("no_telp_guru", txtNoTelpGuru.text.toString())
            .addBodyParameter("gender", gender)
            .addBodyParameter("mata_pelajaran", mataPelajaran)
            .addBodyParameter("hobi", hobi)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(
                        applicationContext,
                        response?.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()

                    if (response?.getString("message")?.contains("berhasil")!!) {
                        this@ManageGuruActivity.finish()
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

    private fun delete() {

        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPointGuru.DELETE + "?nip=" + txtNip.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(
                        applicationContext,
                        response?.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()

                    if (response?.getString("message")?.contains("berhasil")!!) {
                        this@ManageGuruActivity.finish()
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
}
