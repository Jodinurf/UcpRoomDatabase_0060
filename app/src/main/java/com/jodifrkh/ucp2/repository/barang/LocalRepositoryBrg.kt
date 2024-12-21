package com.jodifrkh.ucp2.repository.barang

import com.jodifrkh.ucp2.data.dao.BarangDao
import com.jodifrkh.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg (
    private val barangDao: BarangDao
) : RepositoryBrg {
    override suspend fun insertBarang(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override suspend fun deleteBarang(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBarang(barang: Barang) {
        barangDao.updateBarang(barang)
    }

    override fun getAllBarang(): Flow<List<Barang>> =
        barangDao.getAllBarang()


    override fun getBarang(id: Int): Flow<Barang> =
        barangDao.getBarang(id)

}