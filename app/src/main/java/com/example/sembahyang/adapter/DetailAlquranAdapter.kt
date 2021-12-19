package com.example.sembahyang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sembahyang.databinding.ItemRowAyatBinding
import com.example.sembahyang.model.ModelAyat

class DetailAlquranAdapter : RecyclerView.Adapter<DetailAlquranAdapter.ViewHolder>() {
    private var listener: ((ModelAyat) -> Unit)? = null

    var ayat = mutableListOf<ModelAyat>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(private val binding: ItemRowAyatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ayat: ModelAyat, listener: ((ModelAyat) -> Unit)?) {
            with(binding) {
                tvAyat.text = ayat.ar
                tvTerjemahan.text = ayat.idn
                tvNumber.text = ayat.nomor

            }
            listener?.let {
                itemView.setOnClickListener {
                    listener(ayat)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemRowAyatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ayat[position], listener)
    }

    override fun getItemCount(): Int = ayat.size
}