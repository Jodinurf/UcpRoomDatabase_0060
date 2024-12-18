package com.jodifrkh.ucp2.repository.barang

import com.jodifrkh.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {
    suspend fun insertBarang(barang: Barang)

    suspend fun deleteBarang(barang: Barang)

    suspend fun updateBarang(barang: Barang)

    fun getAllBarang(): Flow<List<Barang>>

    fun getBarang(id: String): Flow<Barang>

}