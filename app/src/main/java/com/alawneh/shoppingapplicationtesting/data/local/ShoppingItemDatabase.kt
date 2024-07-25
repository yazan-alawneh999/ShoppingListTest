package com.alawneh.shoppingapplicationtesting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingDao
import com.alawneh.shoppingapplicationtesting.data.local.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}