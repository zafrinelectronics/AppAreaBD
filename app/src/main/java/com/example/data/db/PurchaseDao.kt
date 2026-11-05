package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Purchase
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchases ORDER BY purchasedAt DESC")
    fun observeAll(): Flow<List<Purchase>>

    @Query("SELECT * FROM purchases WHERE supplierId = :supplierId ORDER BY purchasedAt DESC")
    fun observeBySupplier(supplierId: Long): Flow<List<Purchase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(purchase: Purchase): Long

    @Delete
    suspend fun delete(purchase: Purchase)
}
