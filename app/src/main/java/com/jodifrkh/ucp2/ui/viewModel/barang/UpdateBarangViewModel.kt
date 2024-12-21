package com.jodifrkh.ucp2.ui.viewModel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.repository.barang.RepositoryBrg
import com.jodifrkh.ucp2.ui.navigation.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBarangViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    var updateBrgUiState by mutableStateOf(brgUIState())
        private set

    private val _idBrg : String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.idBrg])

    init {
        viewModelScope.launch {
            updateBrgUiState = repositoryBrg.getBarang(_idBrg.toInt())
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun UpdateStateBrg(barangEvent: BarangEvent) {
        updateBrgUiState = updateBrgUiState.copy(
            barangEvent = barangEvent
        )
    }

    fun validateBrgFields(): Boolean {
        val event = updateBrgUiState.barangEvent
        val errorBrgState = FormErrorBrgState(
            namaBarang = if (event.namaBarang.isNotEmpty()) null else "Nama Barang Tidak Boleh Kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Tidak Boleh Kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Tidak Boleh Kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Tidak Boleh Kosong",
            namaSupplier = if (event.namaSupplier.isNotEmpty()) null else "Nama Supplier Tidak Boleh Kosong"
        )

        updateBrgUiState = updateBrgUiState.copy(isEntryBrgValid = errorBrgState)
        return errorBrgState.isBrgValid()
    }

    fun updateDataBrg() {
        val currentEvent = updateBrgUiState.barangEvent

        if (validateBrgFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.updateBarang(currentEvent.toBarangEntity())
                    updateBrgUiState = updateBrgUiState.copy(
                        snackBarMessage = "Data Berhasi Diupdate",
                        barangEvent = BarangEvent(),
                        isEntryBrgValid = FormErrorBrgState()
                    )
                    println("SnackbarMessageDiatur: ${updateBrgUiState.snackBarMessage}")
                } catch (e:Exception) {
                    updateBrgUiState = updateBrgUiState.copy(
                        snackBarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            updateBrgUiState = updateBrgUiState.copy(
                snackBarMessage = "Data Gagal Diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateBrgUiState = updateBrgUiState.copy(snackBarMessage = null)
    }
}

fun Barang.toUIStateBrg() : brgUIState = brgUIState(
    barangEvent = this.toDetailBrglUiEvent()
)