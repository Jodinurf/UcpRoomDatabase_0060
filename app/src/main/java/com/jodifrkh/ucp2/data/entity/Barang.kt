package com.jodifrkh.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblBarang")
data class Barang(
    @PrimaryKey (autoGenerate = true)
    val id: String,
    val namaBarang: String,
    val deskripsi: String,
    val harga: Int,
    val stok: Int,
    val namaSupplier: String
)
