package com.example.sembahyang.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sembahyang.databinding.ItemRowSurahBinding
import com.example.sembahyang.model.ModelSurah

class AlquranAdapter : RecyclerView.Adapter<AlquranAdapter.ViewHolder>() {
    private var listener: ((ModelSurah) -> Unit)? = null

    var surah = ArrayList<ModelSurah>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(private val binding: ItemRowSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(surah: ModelSurah, listener: ((ModelSurah) -> Unit)?) {
            with(binding) {
                tvNumber.text = surah.nomor
                tvSurah.text = surah.nama
                tvArabic.text = surah.asma
                tvKeterangan.text = surah.type
                tvJmlAyat.text = " | ${surah.ayat} Ayat"


            }
            listener?.let {
                itemView.setOnClickListener {
                    listener(surah)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRowSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(surah[position], listener)

    }

    override fun getItemCount(): Int = surah.size

    fun onClick(listener: ((ModelSurah) -> Unit)?) {
        this.listener = listener
    }

}