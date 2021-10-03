package com.example.notes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int, // primary key which is automatically generated by room library
    val firstName: String,
    val lastName: String,
    val age: Int
) : Parcelable