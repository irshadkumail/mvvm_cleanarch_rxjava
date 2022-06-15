package com.example.mvvm_cleanarchitecture_rxjava.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val phone: String,
    val website: String,
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @SerializedName("geo") val geo: Geo
)

data class Geo(
    val lat: String,
    val lng: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)