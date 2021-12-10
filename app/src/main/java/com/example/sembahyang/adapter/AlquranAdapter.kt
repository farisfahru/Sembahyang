package com.example.sembahyang.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.sembahyang.databinding.ItemRowSurahBinding
import com.example.sembahyang.model.ModelSurah
import java.util.*
import kotlin.collections.ArrayList

class AlquranAdapter : RecyclerView.Adapter<AlquranAdapter.ViewHolder>(), Filterable {
    var surahList = ArrayList<ModelSurah>()
    var surahFiltered = ArrayList<ModelSurah>().apply {
        addAll(surahList)
    }

    var listener: ((ModelSurah) -> Unit)? = null


    inner class ViewHolder(private val binding: ItemRowSurahBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            itemView.setOnClickListener {
                listener?.invoke(surahFiltered[adapterPosition])
            }
        }


        @SuppressLint("SetTextI18n")
        fun bind(surah: ModelSurah) {
            with(binding) {
                tvNumber.text = surah.nomor
                tvSurah.text = surah.nama
                tvArabic.text = surah.asma
                tvKeterangan.text = surah.type
                tvJmlAyat.text = " | ${surah.ayat} Ayat"


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRowSurahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(surahFiltered[position])

    }

    override fun getItemCount(): Int = surahFiltered.size

    fun addData(list: List<ModelSurah>) {
        surahList = list as ArrayList<ModelSurah>
        surahFiltered = surahList
    }

    fun onClick(listener: ((ModelSurah) -> Unit)?) {
        this.listener = listener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    surahFiltered = surahList
                } else {
                    val filteredList = ArrayList<ModelSurah>()
                    for (i in surahList) {
                        when {
                            i.nama.lowercase(Locale.ROOT)
                                .contains(charString.lowercase(Locale.ROOT)) -> {
                                filteredList.add(i)
                            }
                            i.nama.uppercase(Locale.ROOT).contains(charString.uppercase(Locale.ROOT)) -> {
                                filteredList.add(i)
                            }
                            i.nomor!!.contains(constraint.toString()) -> {
                                filteredList.add(i)
                            }
                        }
                    }
                    surahFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = surahFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, result: FilterResults?) {
                surahFiltered = if (result?.values == null)
                    ArrayList()
                else
                    result.values as ArrayList<ModelSurah>
                notifyDataSetChanged()
            }

        }
    }

}