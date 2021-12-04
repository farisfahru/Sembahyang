package com.example.sembahyang.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sembahyang.adapter.DetailAlquranAdapter
import com.example.sembahyang.databinding.ActivityDetailAlquranBinding
import com.example.sembahyang.model.ModelSurah
import com.example.sembahyang.viewmodel.AyatViewModel

class DetailAlquranActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlquranBinding
    private lateinit var detailAlquranAdapter: DetailAlquranAdapter
    private lateinit var ayatViewModel: AyatViewModel
    private var bundle: Bundle? = null


    companion object {
        const val EXTRA_SURAH = "extra_surah"
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlquranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailAlquranAdapter = DetailAlquranAdapter()
        ayatViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[AyatViewModel::class.java]
        val layoutManager = LinearLayoutManager(this)
        binding.rvSurah.layoutManager = layoutManager
        binding.rvSurah.adapter = detailAlquranAdapter

        sendBundle()
        getDataAyat()
        onActions()

    }

    private fun sendBundle() {
        val surah = intent?.getParcelableExtra<ModelSurah>(EXTRA_SURAH) as ModelSurah
        bundle?.putString(EXTRA_SURAH, surah.nama)
    }

    private fun getDataAyat() {
        intent?.let {
            val surah = it.getParcelableExtra<ModelSurah>(EXTRA_SURAH)
            if (surah != null) {
                val nomor = surah.nomor
                ayatViewModel.setDetailSurah(nomor)
                ayatViewModel.getDetailSurah().observe(this) { dataAyat ->
                    if (dataAyat != null) {
                        with(binding) {
                            showLoading()
                            tvDetailTitle.text = surah.nama
                            tvSurah.text = surah.asma
                            detailAlquranAdapter.ayat = dataAyat
                            rvSurah.adapter = detailAlquranAdapter
                            binding.rvSurah.setHasFixedSize(true)
                            hideLoading()

                        }
                    }
                }
            }
        }
    }

    private fun onActions(){
        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun showLoading() {
        binding.loadingProgress.show()
    }

    private fun hideLoading() {
        binding.loadingProgress.hide()
    }
}