package com.iot.login_java_kotlin

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by User on 2017-06-05.
 */

data class ParcelData ( var _poweron : Int , var _msg : String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ParcelData> = object : Parcelable.Creator<ParcelData> {

            override fun createFromParcel(source: Parcel): ParcelData = ParcelData(source)
            override fun newArray(size: Int): Array<ParcelData?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(_poweron)
        dest.writeString(_msg)
    }
}