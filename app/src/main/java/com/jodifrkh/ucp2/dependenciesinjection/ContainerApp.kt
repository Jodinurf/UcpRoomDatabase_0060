package com.jodifrkh.ucp2.dependenciesinjection

import android.content.Context
import com.jodifrkh.ucp2.data.database.GudangDatabase
import com.jodifrkh.ucp2.repository.barang.LocalRepositoryBrg
import com.jodifrkh.ucp2.repository.barang.RepositoryBrg
import com.jodifrkh.ucp2.repository.supplier.LocalRepositorySpl
import com.jodifrkh.ucp2.repository.supplier.RepositorySpl

interface InterfaceContainerApp {
    val repositorySpl : RepositorySpl
    val repositoryBrg : RepositoryBrg
}

class ContainerApp (private val context : Context) :
    InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(GudangDatabase.getDatabase(context).barangDao())
    }
    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(GudangDatabase.getDatabase(context).supplierDao())
    }
}