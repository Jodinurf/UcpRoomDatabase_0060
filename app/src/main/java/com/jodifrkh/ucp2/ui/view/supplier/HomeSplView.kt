package com.jodifrkh.ucp2.ui.view.supplier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jodifrkh.ucp2.R
import com.jodifrkh.ucp2.data.entity.Supplier
import com.jodifrkh.ucp2.ui.customwidget.LoadingState
import com.jodifrkh.ucp2.ui.customwidget.TopAppBar
import com.jodifrkh.ucp2.ui.viewModel.supplier.SupplierHomeViewModel
import com.jodifrkh.ucp2.ui.viewModel.PenyediaViewModel
import com.jodifrkh.ucp2.ui.viewModel.supplier.HomeUIStateSpl
import kotlinx.coroutines.launch

@Composable
fun HomeSplView(
    modifier: Modifier = Modifier,
    viewModel: SupplierHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddSplClick: () -> Unit = {},
    onDetailSplClick: (String) -> Unit = {},
    onBackArrow: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Data Supplier",
                onBackClick = onBackArrow,
                actionIcon = R.drawable.supplier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSplClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Supplier"
                )
            }
        }
    ) {

    }
}

@Composable
fun BodyHomeSplView(
    homeUiState: HomeUIStateSpl,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when {
        homeUiState.isLoading -> {
            LoadingState()
        }

        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeUiState.listSpl.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Supplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListSupplier(
                listSpl = homeUiState.listSpl,
                onClick = onClick,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSupplier(
    listSpl: List<Supplier>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listSpl,
            itemContent = { spl ->
                CardSupplier(
                    spl = spl,
                    onClick = { onClick(spl.id.toString()) }
                )
            }
        )
    }
}

@Composable
fun CardSupplier(
    spl: Supplier,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.itemsupplier),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = spl.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Id : " + spl.id.toString(),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Text(
                    text = spl.alamat,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(4.dp))

            }
        }
    }
}


