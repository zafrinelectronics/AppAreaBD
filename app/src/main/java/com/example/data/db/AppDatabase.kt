package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.AccountEntry
import com.example.data.model.AppUser
import com.example.data.model.Customer
import com.example.data.model.Employee
import com.example.data.model.Expense
import com.example.data.model.Product
import com.example.data.model.Purchase
import com.example.data.model.SalaryPayment
import com.example.data.model.Sale
import com.example.data.model.Supplier

@Database(
    entities = [
        Product::class, Sale::class, Expense::class, Employee::class,
        SalaryPayment::class, AppUser::class, Customer::class, Supplier::class,
        Purchase::class, AccountEntry::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun saleDao(): SaleDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun userDao(): UserDao
    abstract fun customerDao(): CustomerDao
    abstract fun supplierDao(): SupplierDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun accountEntryDao(): AccountEntryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS customers (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        phone TEXT NOT NULL,
                        address TEXT NOT NULL,
                        email TEXT NOT NULL,
                        nid TEXT NOT NULL,
                        openingBalance REAL NOT NULL,
                        createdAt INTEGER NOT NULL
                    )
                """.trimIndent())
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS suppliers (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        phone TEXT NOT NULL,
                        address TEXT NOT NULL,
                        email TEXT NOT NULL,
                        openingBalance REAL NOT NULL,
                        createdAt INTEGER NOT NULL
                    )
                """.trimIndent())
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS purchases (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        supplierId INTEGER NOT NULL,
                        supplierName TEXT NOT NULL,
                        invoiceNumber TEXT NOT NULL,
                        subtotal REAL NOT NULL,
                        discount REAL NOT NULL,
                        totalAmount REAL NOT NULL,
                        paidAmount REAL NOT NULL,
                        dueAmount REAL NOT NULL,
                        status TEXT NOT NULL,
                        notes TEXT NOT NULL,
                        purchasedAt INTEGER NOT NULL
                    )
                """.trimIndent())
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS account_entries (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        entryType TEXT NOT NULL,
                        category TEXT NOT NULL,
                        amount REAL NOT NULL,
                        accountName TEXT NOT NULL,
                        referenceType TEXT NOT NULL,
                        referenceId INTEGER NOT NULL,
                        note TEXT NOT NULL,
                        occurredAt INTEGER NOT NULL
                    )
                """.trimIndent())
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "apple_area_bd_database"
                )
                    .addMigrations(MIGRATION_4_5)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
