package com.jodifrkh.ucp2.ui.viewModel.supplier

import com.jodifrkh.ucp2.data.entity.Supplier

data class HomeUIState (
    val listSpl : List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
