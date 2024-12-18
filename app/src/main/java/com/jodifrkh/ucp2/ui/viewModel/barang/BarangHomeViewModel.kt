package com.jodifrkh.ucp2.ui.viewModel.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.repository.barang.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class BarangHomeViewModel(
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {
    val homeUiState: StateFlow<HomeUIState> = repositoryBrg.getAllBarang()
        .filterNotNull()
        .map {
            HomeUIState (
                listBarang = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUIState(isLoading = true))
            delay(900)
        }
        .catch {
            HomeUIState(
                isLoading = false,
                isError = true,
                errorMessage = it.message?: "Terjadi Kesalahan"
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUIState(
                isLoading = true
            )
        )
}

data class HomeUIState (
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)