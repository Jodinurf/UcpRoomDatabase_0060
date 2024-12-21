package com.jodifrkh.ucp2.ui.view.barang

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jodifrkh.ucp2.ui.viewModel.barang.BarangEvent
import com.jodifrkh.ucp2.ui.viewModel.barang.FormErrorBrgState
import com.jodifrkh.ucp2.ui.viewModel.barang.brgUIState

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: brgUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorBrgState = uiState.isEntryBrgValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Simpan",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}


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