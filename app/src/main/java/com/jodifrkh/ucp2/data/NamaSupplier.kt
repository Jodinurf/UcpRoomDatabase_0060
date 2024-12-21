package com.jodifrkh.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jodifrkh.ucp2.ui.viewModel.barang.BarangViewModel

object NamaSupplier {
    @Composable
    fun options(): List<String> {
        val supplierViewModel: BarangViewModel = viewModel()
        val supplierNames by supplierViewModel.supplierNames.collectAsState()
        return supplierNames
    }
}
