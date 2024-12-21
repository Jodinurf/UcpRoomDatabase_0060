package com.jodifrkh.ucp2.repository.supplier

import com.jodifrkh.ucp2.data.dao.SupplierDao
import com.jodifrkh.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val supplierDao: SupplierDao
) : RepositorySpl {

    override suspend fun insertSupplier(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }

    override fun getAllSupplier(): Flow<List<Supplier>>  =
        supplierDao.getAllSupplier()

    override fun getSupplierNama(): Flow<List<String>> =
        supplierDao.getSupplierNama()
}