package com.example.sembahyang.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelAyat(
    @SerializedName("ar")
    var arab: String? = null,

    @SerializedName("id")
    var terjemahan: String? = null,

    @SerializedName("nomor")
    var nomor: String? = null
) : Parcelable
