package by.tms.sofasstore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sofa(val name: String, val price: Double, val image: Int) : Parcelable {

    override fun toString(): String {
        return name
    }
}