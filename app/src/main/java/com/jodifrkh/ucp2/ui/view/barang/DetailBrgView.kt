package com.jodifrkh.ucp2.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.ui.customwidget.LoadingState
import com.jodifrkh.ucp2.ui.viewModel.barang.DetailBrgUiState
import com.jodifrkh.ucp2.ui.viewModel.barang.toBarangEntity

@Composable
fun BarangDetailBody(
    modifier: Modifier = Modifier,
    detailBrgUiState: DetailBrgUiState
) {
    when {
        detailBrgUiState.isLoading -> {
            LoadingState()
        }


        detailBrgUiState.isUiBarangEventNotEmpty ->
        {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailBrg(
                    barang = detailBrgUiState.detailUiBrgEvent.toBarangEntity(),
                    modifier = modifier
                )
            }
        }
    }

}

@Composable
fun ItemDetailBrg(
    modifier: Modifier = Modifier,
    barang: Barang
) {
    Card (
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column (
            modifier = Modifier.padding(12.dp)
        ) {
            ComponentDetailBrg(title = "ID", content = barang.id.toString())
            Spacer(modifier = Modifier.padding(16.dp))
            ComponentDetailBrg(title = "Nama Barang", content = barang.namaBarang)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(title = "Deskripsi", content = barang.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(title = "Harga", content = barang.harga.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(title = "Stok", content = barang.stok.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(title = "Nama Supplier", content = barang.namaSupplier)
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }

}

@Composable
fun ComponentDetailBrg(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    Column (
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "$title :",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = content,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}