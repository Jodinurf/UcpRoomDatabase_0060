package com.jodifrkh.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jodifrkh.ucp2.data.dao.BarangDao
import com.jodifrkh.ucp2.data.dao.SupplierDao
import com.jodifrkh.ucp2.data.entity.Barang
import com.jodifrkh.ucp2.data.entity.Supplier

@Database(entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)
abstract class GudangDatabase : RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun supplierDao(): SupplierDao

    companion object {
        @Volatile
        private var Instance: GudangDatabase? = null

        fun getDatabase(context: Context): GudangDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    GudangDatabase::class.java,
                    "GudangDatabase"
                ).build().also { Instance = it }
            }
        }
    }
}
