package com.jodifrkh.ucp2.ui.view.barang

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jodifrkh.ucp2.ui.viewModel.barang.BarangEvent
import com.jodifrkh.ucp2.ui.viewModel.barang.FormErrorBrgState


@Preview(showBackground = true)
@Composable
fun FormBarang(
    modifier: Modifier = Modifier,
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorBrgState: FormErrorBrgState = FormErrorBrgState(),
) {
     Column (
         modifier = modifier.fillMaxWidth()
     ) {
         OutlinedTextField(
             modifier = Modifier.fillMaxWidth(),
             value = barangEvent.namaBarang,
             onValueChange = {
                 onValueChange(barangEvent.copy(namaBarang = it))
                },
             label = { Text("Nama Barang")},
             isError = errorBrgState.namaBarang != null,
             placeholder = { Text("Masukkan Nama Barang")}
         )
         Text(
             text = errorBrgState.namaBarang ?: "",
             color = Color.Red
         )
     }
}