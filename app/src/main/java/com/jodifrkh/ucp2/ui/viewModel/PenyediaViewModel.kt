package com.jodifrkh.ucp2.ui.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jodifrkh.ucp2.GudangApp
import com.jodifrkh.ucp2.ui.viewModel.barang.*
import com.jodifrkh.ucp2.ui.viewModel.supplier.*


object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            SupplierViewModel(
                GudangApp().containerApp.repositorySpl
            )
        }
        initializer {
            SupplierHomeViewModel(
                GudangApp().containerApp.repositorySpl
            )
        }
        initializer {
            BarangViewModel(
                GudangApp().containerApp.repositoryBrg,
            )
        }
        initializer {
            BarangHomeViewModel(
                GudangApp().containerApp.repositoryBrg
            )
        }
        initializer {
            DetailBarangViewModel(
                createSavedStateHandle(),
                GudangApp().containerApp.repositoryBrg
            )
        }
        initializer {
           UpdateBarangViewModel(
                createSavedStateHandle(),
                GudangApp().containerApp.repositoryBrg,
            )
        }
    }
}

fun CreationExtras.GudangApp() : GudangApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GudangApp)