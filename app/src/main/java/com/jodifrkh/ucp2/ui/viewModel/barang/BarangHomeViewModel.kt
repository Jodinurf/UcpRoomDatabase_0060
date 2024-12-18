package com.jodifrkh.ucp2.ui.viewModel.barang

import com.jodifrkh.ucp2.data.entity.Barang

data class HomeUIState (
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)