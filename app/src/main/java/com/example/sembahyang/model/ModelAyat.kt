package com.example.sembahyang.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelAyat(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("nomor")
    var nomor: String? = null,

    @SerializedName("ar")
    var ar: String? = null,

    @SerializedName("idn")
    var idn: String? = null
) : Parcelable
