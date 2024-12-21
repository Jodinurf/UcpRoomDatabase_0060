package com.jodifrkh.ucp2.data.dao

import androidx.room.*
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {

    @Insert
    suspend fun insertSupplier(supplier: Supplier)

    @Query("SELECT * FROM tblSupplier ORDER BY nama")
    fun getAllSupplier(): Flow<List<Supplier>>

    @Query("SELECT nama FROM tblSupplier")
    fun getSupplierNama(): Flow<List<String>>

}