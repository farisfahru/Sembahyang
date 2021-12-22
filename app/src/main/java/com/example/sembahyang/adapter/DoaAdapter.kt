package com.example.sembahyang.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.sembahyang.databinding.ItemRowDoaBinding
import com.example.sembahyang.model.ModelDoa
import java.util.*
import kotlin.collections.ArrayList

class DoaAdapter : RecyclerView.Adapter<DoaAdapter.ViewHolder>(), Filterable {
    var doaList = ArrayList<ModelDoa>()
    var doaFiltered = ArrayList<ModelDoa>().apply {
        addAll(doaList)
    }

    var listener: ((ModelDoa) -> Unit)? = null


    inner class ViewHolder(private val binding: ItemRowDoaBinding) :
        RecyclerView.ViewHolder(binding.root) {


        init {
            itemView.setOnClickListener {
                listener?.invoke(doaFiltered[adapterPosition])
            }
        }


        @SuppressLint("SetTextI18n")
        fun bind(doa: ModelDoa) {
            with(binding) {
                tvNumber.text = doa.id
                tvDoa.text = doa.doa
                tvAyat.text = doa.ayat
                tvTerjemahan.text = doa.artinya


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRowDoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(doaFiltered[position])
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#4FCCB154"))
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#33CCB154"))
        }
    }

    override fun getItemCount(): Int = doaFiltered.size

    fun addData(list: List<ModelDoa>) {
        doaList = list as ArrayList<ModelDoa>
        doaFiltered = doaList
    }

    fun onClick(listener: ((ModelDoa) -> Unit)?) {
        this.listener = listener
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    doaFiltered = doaList
                } else {
                    val filteredList = ArrayList<ModelDoa>()
                    for (i in doaList) {
                        when {
                            i.doa?.lowercase(Locale.ROOT)
                                ?.contains(charString.lowercase(Locale.ROOT))!! -> {
                                filteredList.add(i)
                            }
                            i.doa?.uppercase(Locale.ROOT)!!
                                .contains(charString.uppercase(Locale.ROOT)) -> {
                                filteredList.add(i)
                            }
                            i.id!!.contains(constraint.toString()) -> {
                                filteredList.add(i)
                            }
                        }
                    }
                    doaFiltered = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = doaFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, result: FilterResults?) {
                doaFiltered = if (result?.values == null)
                    ArrayList()
                else
                    result.values as ArrayList<ModelDoa>
                notifyDataSetChanged()
            }

        }
    }

}