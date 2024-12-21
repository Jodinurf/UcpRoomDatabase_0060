package com.jodifrkh.ucp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jodifrkh.ucp2.ui.view.Homepage
import com.jodifrkh.ucp2.ui.view.barang.HomeBrgView
import com.jodifrkh.ucp2.ui.view.barang.InsertBrgView
import com.jodifrkh.ucp2.ui.view.supplier.HomeSplView
import com.jodifrkh.ucp2.ui.view.supplier.InsertSplView

@Composable
fun MainControllerPage(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            Homepage(
                onItemClick = { item ->
                    when (item) {
                        "Supplier" -> navController.navigate(DestinasiHomeSpl.route)
                        "Barang" -> navController.navigate(DestinasiHomeBrg.route)
                        else -> {}
                    }
                }
            )
        }
        composable(
            route = DestinasiHomeSpl.route
        ) {
            HomeSplView(
                onAddSplClick = {
                    navController.navigate(DestinasiInsertSpl.route)
                },
                onBackArrow = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertSpl.route
        ) {
            InsertSplView(
                onBackArrow = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
        composable(
            route = DestinasiHomeBrg.route
        ) {
            HomeBrgView(
                onAddBrgClick = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                onDetailBrgClick = { idBrg ->
                    navController.navigate("${DestinasiDetailBrg.route}/$idBrg")
                },
                onBackArrow = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = DestinasiInsertBrg.route
        ) {
            InsertBrgView(
                onBackArrow = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = Modifier
            )
        }
    }
}