package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_entries")
data class AccountEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val entryType: String,
    val category: String,
    val amount: Double,
    val accountName: String = "Cash",
    val referenceType: String = "",
    val referenceId: Long = 0,
    val note: String = "",
    val occurredAt: Long = System.currentTimeMillis()
)
