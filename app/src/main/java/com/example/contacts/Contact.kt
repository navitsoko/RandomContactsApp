package com.example.contacts

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String,
    val name: String,
    val email: String,
    val birthday: String
): Parcelable

@Parcelize
data class ApiContact(
    @PrimaryKey(autoGenerate = true)
    val id: Id,
    val picture: Image,
     val name: Name,
    val email: String,
    val dob: Birthday
): Parcelable

@Parcelize
data class Name(
    val title: String,
    val first: String,
    val last: String
): Parcelable

@Parcelize
data class Image(
    val large: String,
    val medium: String,
    val thumbnail: String
): Parcelable

@Parcelize
data class Birthday(
    val date: String,
    val age: String
): Parcelable

@Parcelize
data class Id(
    val name: String,
    val value: String
): Parcelable


data class ContactResponse(
    val results: List<ApiContact>,
    val info: Info
)

data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
