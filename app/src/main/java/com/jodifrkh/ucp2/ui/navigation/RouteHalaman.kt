package com.jodifrkh.ucp2.ui.navigation

interface HalamanController {
    val route : String
}

object DestinasiHome : HalamanController {
    override val route = "home"
}

object DestinasiHomeSpl : HalamanController {
    override val route = "supplier"
}

object DestinasiInsertSpl : HalamanController {
    override val route = "supplier/add"
}
