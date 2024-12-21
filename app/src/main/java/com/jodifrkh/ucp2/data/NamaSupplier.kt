package com.jodifrkh.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jodifrkh.ucp2.ui.viewModel.PenyediaViewModel
import com.jodifrkh.ucp2.ui.viewModel.supplier.SupplierHomeViewModel

object NamaSupplier {
    @Composable
    fun options(
        supplierHomeViewModel: SupplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val dataNama by supplierHomeViewModel.homeUiStateSpl.collectAsState()
        val namaSupplier = dataNama.listSpl.map { it.nama }
        return namaSupplier
    }
}
