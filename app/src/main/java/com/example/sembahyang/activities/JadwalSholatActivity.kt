package com.example.sembahyang.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.sembahyang.R
import com.example.sembahyang.databinding.ActivityJadwalSholatBinding
import com.example.sembahyang.model.ModelKota
import com.example.sembahyang.utils.ClientAsyncTask
import com.example.sembahyang.utils.DatePickerFragment
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class JadwalSholatActivity : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener {

    private lateinit var binding: ActivityJadwalSholatBinding
    private lateinit var listModelKota: MutableList<ModelKota>
    private lateinit var modelKotaAdapter: ArrayAdapter<ModelKota>
    private lateinit var progressDialog: ProgressDialog

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalSholatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        binding.tvDate.text = currentDate.format(Date())

        binding.btnDate.setOnClickListener(this)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan jadwal...")

        //show data spinner
        showDataSpinner()

        //get data kota
        getDataKota()

        return

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_date -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
        }

    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        binding.tvDate.text = dateFormat.format(calendar.time)
        showDataSpinner()
        getDataKota()
    }

    private fun showDataSpinner() {
        val spKota: Spinner = binding.spinCity
        listModelKota = ArrayList()
        modelKotaAdapter = ArrayAdapter(
            getApplicationContext(),
            android.R.layout.simple_spinner_item,
            listModelKota as ArrayList<ModelKota>
        )
        modelKotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spKota.adapter = modelKotaAdapter
        spKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>) {}
            override fun onItemSelected(p0: AdapterView<*>, view: View?, position: Int, id: Long) {
                val spinKota = modelKotaAdapter.getItem(position)
                getDataJadwal(spinKota?.id)
            }
        }
    }

    private fun getDataJadwal(id: Int?) {
        try {
            progressDialog.show()
            val idKota = id.toString()
            val tvDate = binding.tvDate.text.toString()
            val tanggal = tvDate.format(Date())
            val url = "https://api.banghasan.com/sholat/format/json/jadwal/kota/$idKota/tanggal/$tanggal"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener {
                override fun onPostExecute(result: String) {
                    try {
                        progressDialog.dismiss()
                        val jsonObject = JSONObject(result)
                        val strJadwal = jsonObject.getJSONObject("jadwal")
                        val strData = strJadwal.getJSONObject("data")

                        binding.tvSubuh.text = strData.getString("subuh")
                        binding.tvDzuhur.text = strData.getString("dzuhur")
                        binding.tvAshar.text = strData.getString("ashar")
                        binding.tvMaghrib.text = strData.getString("maghrib")
                        binding.tvIsya.text = strData.getString("isya")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getDataKota() {
        try {
            val url = "https://api.banghasan.com/sholat/format/json/kota"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener {
                override fun onPostExecute(result: String) {
                    try {
                        val jsonObject = JSONObject(result)
                        val jsonArray = jsonObject.getJSONArray("kota")
                        var daftarKota: ModelKota?
                        for (i in 0 until jsonArray.length()) {
                            val obj = jsonArray.getJSONObject(i)
                            daftarKota = ModelKota()
                            daftarKota.id = obj.getInt("id")
                            daftarKota.nama = obj.getString("nama")
                            listModelKota.add(daftarKota)
                        }
                        modelKotaAdapter.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}