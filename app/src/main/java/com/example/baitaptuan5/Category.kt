package com.example.baitaptuan5

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    var categoryId: Int,
    var categoryName: String
) : Parcelable