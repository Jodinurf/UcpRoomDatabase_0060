package com.jodifrkh.ucp2.ui.view.barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jodifrkh.ucp2.R
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.ui.customwidget.LoadingState
import com.jodifrkh.ucp2.ui.customwidget.TopAppBar
import com.jodifrkh.ucp2.ui.viewModel.PenyediaViewModel
import com.jodifrkh.ucp2.ui.viewModel.barang.DetailBarangViewModel
import com.jodifrkh.ucp2.ui.viewModel.barang.DetailBrgUiState
import com.jodifrkh.ucp2.ui.viewModel.barang.toBarangEntity

@Composable
fun DetailBrgView(
    modifier: Modifier = Modifier,
    viewModel: DetailBarangViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBackArrow: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
) {
    Scaffold (
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = "Detail Data Barang",
                onBackClick = onBackArrow,
                actionIcon = R.drawable.box
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailBrgUiState.value.detailUiBrgEvent.id)
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
        }
    ) { innerPadding->
        val detailBrgUiState by viewModel.detailBrgUiState.collectAsState()

        BarangDetailBody(
            modifier = Modifier.padding(innerPadding),
            detailBrgUiState = detailBrgUiState,
            onDeleteClick = {
                viewModel.deleteBrg()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BarangDetailBody(
    modifier: Modifier = Modifier,
    detailBrgUiState: DetailBrgUiState,
    onDeleteClick: () -> Unit = { }
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
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
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text("Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false},
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailBrgUiState.isUiBarangEmpty -> {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak Ditemukan",
                    modifier = Modifier.padding(16.dp)
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
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { },
        title = { Text("Delete Data")},
        text = { Text("Apakah anda yakin ingin menghapus data barang ini?")},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        }
    )
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