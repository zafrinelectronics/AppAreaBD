package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val supplierId: Long = 0,
    val supplierName: String = "",
    val invoiceNumber: String,
    val subtotal: Double = 0.0,
    val discount: Double = 0.0,
    val totalAmount: Double,
    val paidAmount: Double = 0.0,
    val dueAmount: Double = 0.0,
    val status: String = "OPEN",
    val notes: String = "",
    val purchasedAt: Long = System.currentTimeMillis()
)
