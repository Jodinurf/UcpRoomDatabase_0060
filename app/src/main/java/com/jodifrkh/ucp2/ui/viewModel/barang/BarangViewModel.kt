package com.jodifrkh.ucp2.ui.viewModel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.repository.barang.RepositoryBrg
import com.jodifrkh.ucp2.repository.supplier.RepositorySpl
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BarangViewModel(private val repositoryBrg: RepositoryBrg, repositorySpl: RepositorySpl) : ViewModel() {
    var uiBrgState by mutableStateOf(brgUIState())

    val supplierNames: StateFlow<List<String>> = repositorySpl.getSupplierNama()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateBrgState(barangEvent: BarangEvent) {
        uiBrgState = uiBrgState.copy(barangEvent = barangEvent)
    }

    private fun validateBrgFields(): Boolean {
        val event = uiBrgState.barangEvent
        val errorBrgState = FormErrorBrgState(
            namaBarang = if (event.namaBarang.isNotEmpty()) null else "Nama Barang Tidak Boleh Kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Tidak Boleh Kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Tidak Boleh Kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Tidak Boleh Kosong",
            namaSupplier = if (event.namaSupplier.isNotEmpty()) null else "Nama Supplier Tidak Boleh Kosong"
        )

        uiBrgState = uiBrgState.copy(isEntryBrgValid = errorBrgState)
        return errorBrgState.isBrgValid()
    }

    fun saveDataBrg() {
        val currentBrgEvent = uiBrgState.barangEvent

        if (validateBrgFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBarang(currentBrgEvent.toBarangEntity())
                    uiBrgState = uiBrgState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        barangEvent = BarangEvent(),
                        isEntryBrgValid = FormErrorBrgState()
                    )
                } catch (e: Exception) {
                    uiBrgState = uiBrgState.copy(snackBarMessage = "Data Barang Gagal Disimpan")
                }
            }
        } else {
            uiBrgState = uiBrgState.copy(snackBarMessage = "Input tidak valid. Periksa Data Kembali")
        }
    }

    fun resetSnackBarBrgMessage() {
        uiBrgState = uiBrgState.copy(snackBarMessage = null)
    }

}

data class brgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryBrgValid: FormErrorBrgState = FormErrorBrgState(),
    val snackBarMessage: String? = null
)

data class FormErrorBrgState(
    val id: String? = null,
    val namaBarang: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSupplier: String? = null
) {
    fun isBrgValid(): Boolean {
        return id == null && namaBarang == null && harga == null &&
                deskripsi == null && stok == null && namaSupplier == null
    }
}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id.toIntOrNull() ?: 0,
    namaBarang = namaBarang,
    deskripsi = deskripsi,
    harga = harga.toIntOrNull() ?: 0,
    stok = stok.toIntOrNull() ?: 0,
    namaSupplier = namaSupplier
)

data class BarangEvent(
    val id: String = "",
    val namaBarang: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    var namaSupplier: String = ""
)