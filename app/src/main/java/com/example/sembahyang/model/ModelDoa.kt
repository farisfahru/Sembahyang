package com.example.sembahyang.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelDoa(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("doa")
    var doa: String? = null,

    @SerializedName("ayat")
    var ayat: String? = null,

    @SerializedName("latin")
    var latin: String? = null,

    @SerializedName("artinya")
    var artinya: String? = null
) : Parcelable
