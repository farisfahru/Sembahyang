package com.example.sembahyang.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelKota(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("nama")
    var nama: String? = null
) : Parcelable {

    override fun toString(): String {
        return nama.toString()
    }
}
