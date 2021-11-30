package com.example.sembahyang.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelSurah(
    @SerializedName("arti")
    var arti: String? = null,

    @SerializedName("asma")
    var asma: String? = null,

    @SerializedName("ayat")
    var ayat: String? = null,

    @SerializedName("nama")
    var nama: String,

    @SerializedName("type")
    var type: String? = null,

    @SerializedName("audio")
    var audio: String? = null,

    @SerializedName("nomor")
    var nomor: String? = null,

    @SerializedName("keterangan")
    var keterangan: String? = null
) : Parcelable