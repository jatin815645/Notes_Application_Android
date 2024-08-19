package com.smartherd.notesappmvvm.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Notes")
@Parcelize
class Notes (

    @PrimaryKey(autoGenerate = true)
    var Id:Int? = null,

    var title : String,
    var subtitle : String,
    var notes : String,
    var date : String,
    var priority : String
) : Parcelable