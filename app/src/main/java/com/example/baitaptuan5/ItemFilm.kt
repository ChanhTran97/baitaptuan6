package com.example.baitaptuan5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    var imageId: Int,
    var title: String,
    var price: Double,
    var category: Category
) : Parcelable