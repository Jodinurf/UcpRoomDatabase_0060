package com.jodifrkh.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblSupplier")
data class Supplier(
    @PrimaryKey (autoGenerate = true)
    val id: String,
    val nama: String,
    val kontak: String,
    val alamat: String
)