package com.example.sembahyang.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sembahyang.adapter.DoaAdapter
import com.example.sembahyang.databinding.ActivityDoaBinding
import com.example.sembahyang.model.ModelDoa
import com.example.sembahyang.viewmodel.DoaViewModel

class DoaActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityDoaBinding
    private lateinit var doaViewModel: DoaViewModel
    private lateinit var doaAdapter: DoaAdapter

    companion object {
        const val EXTRA_DOA = "extra_doa"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doaAdapter = DoaAdapter()
        doaViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DoaViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvDoa.layoutManager = layoutManager
        binding.rvDoa.setHasFixedSize(true)
        binding.rvDoa.adapter = doaAdapter

        getDataDoa()

        onAction()

    }

    private fun getDataDoa() {
        showLoading()
        val doa = intent.getParcelableExtra<ModelDoa>(EXTRA_DOA).toString()
        doaViewModel.setDoa(doa)
        doaViewModel.getDoa().observe(this) {
            if (it != null) {
                doaAdapter.addData(it)
                doaAdapter.notifyDataSetChanged()
                binding.rvDoa.adapter = doaAdapter
                hideLoading()
            }
        }
    }

    private fun onAction() {
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        doaAdapter.filter.filter(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        doaAdapter.filter.filter(newText)
        return true
    }
}