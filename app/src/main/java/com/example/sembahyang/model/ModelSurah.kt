package com.example.sembahyang.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelSurah(
    @SerializedName("nomor")
    var nomor: String? = null,

    @SerializedName("nama")
    var nama: String? = null,

    @SerializedName("nama_latin")
    var nama_latin: String,

    @SerializedName("tempat_turun")
    var tempat_turun: String? = null,

    @SerializedName("arti")
    var arti: String? = null,

    @SerializedName("jumlah_ayat")
    var jumlah_ayat: String? = null,

    @SerializedName("deskripsi")
    var deskripsi: String? = null,

    @SerializedName("keterangan")
    var audio: String? = null
) : Parcelable