package com.jodifrkh.ucp2.repository.supplier

import com.jodifrkh.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun insertSupplier(supplier: Supplier)

    fun getAllSupplier(): Flow<List<Supplier>>

    fun getSupplierNama(): Flow<List<String>>

}