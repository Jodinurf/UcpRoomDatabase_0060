package com.jodifrkh.ucp2.data.dao

import androidx.room.*
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {

    @Insert
    suspend fun inserSupplier(supplier: Supplier)

    @Query("SELECT * FROM tblSupplier ORDER BY nama")
    fun getAllSupplier(): Flow<List<Supplier>>

    @Query("SELECT * FROM tblSupplier WHERE id = :id")
    fun getSupplier(id: String): Flow<Supplier>
}