package com.example.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.AccountEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountEntryDao {
    @Query("SELECT * FROM account_entries ORDER BY occurredAt DESC")
    fun observeAll(): Flow<List<AccountEntry>>

    @Query("SELECT * FROM account_entries WHERE entryType = :entryType ORDER BY occurredAt DESC")
    fun observeByType(entryType: String): Flow<List<AccountEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entry: AccountEntry): Long

    @Delete
    suspend fun delete(entry: AccountEntry)
}
