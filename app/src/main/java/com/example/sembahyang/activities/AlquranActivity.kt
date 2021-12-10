package com.example.sembahyang.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sembahyang.adapter.AlquranAdapter
import com.example.sembahyang.databinding.ActivityAlquranBinding
import com.example.sembahyang.model.ModelSurah
import com.example.sembahyang.viewmodel.SurahViewModel
import org.jetbrains.anko.startActivity

class AlquranActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    private lateinit var binding: ActivityAlquranBinding
    private lateinit var alquranAdapter : AlquranAdapter
    private lateinit var surahViewModel: SurahViewModel

    companion object {
        const val EXTRA_SURAH = "extra_surah"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlquranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        alquranAdapter = AlquranAdapter()
        surahViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SurahViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvSurah.layoutManager = layoutManager
        binding.rvSurah.setHasFixedSize(true)
        binding.rvSurah.adapter = alquranAdapter
        getDataSurah()

        onAction()

    }

    private fun getDataSurah() {
        showLoading()
        val surah = intent.getParcelableExtra<ModelSurah>(EXTRA_SURAH).toString()
        surahViewModel.setSurah(surah)
        surahViewModel.getSurah().observe(this){
            if (it != null ){
                alquranAdapter.addData(it)
                alquranAdapter.notifyDataSetChanged()
                binding.rvSurah.adapter = alquranAdapter
                hideLoading()
            }
        }
    }

    private fun onAction() {
        with(binding) {
            alquranAdapter.onClick { surah ->
                startActivity<DetailAlquranActivity>(
                    DetailAlquranActivity.EXTRA_SURAH to surah
                )
            }

            searchView.setOnSearchClickListener {
                tvTitle.gone()
                searchView.maxWidth
            }



            searchView.setOnCloseListener {
                tvTitle.visible()
                false
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    alquranAdapter.filter.filter(query)
                    return false
                }


                override fun onQueryTextChange(newText: String?): Boolean {
                    alquranAdapter.filter.filter(newText)
                    return false
                }
            })
        }
    }

    private fun showLoading() {
        binding.loadingProgress.show()
    }

    private fun hideLoading() {
        binding.loadingProgress.hide()
        binding
    }

    private fun View.gone() {
        visibility = View.GONE
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        alquranAdapter.filter.filter(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        alquranAdapter.filter.filter(newText)
        return true
    }



}