package com.jodifrkh.ucp2.ui.viewModel.supplier

import com.jodifrkh.ucp2.data.entity.Barang

fun Barang.toDetaiUIEvent() : BarangEvent {
    return BarangEvent(
        id = id,
        namaBarang = namaBarang,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namaSupplier = namaSupplier
    )
}