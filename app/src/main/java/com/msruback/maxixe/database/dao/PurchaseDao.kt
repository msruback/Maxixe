package com.msruback.maxixe.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.msruback.maxixe.database.entities.Purchase
import com.msruback.maxixe.database.entities.PurchaseCharacter
import com.msruback.maxixe.database.entities.PurchaseTag
import com.msruback.maxixe.database.queries.EverythingPurchase
import com.msruback.maxixe.database.queries.PurchaseSellerEvent

@Dao
interface PurchaseDao {
    @Query("SELECT COUNT() FROM Purchases")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(purchase: Purchase): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCharacters(vararg purchaseCharacters: PurchaseCharacter)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTags(vararg purchaseTags: PurchaseTag)

    @Transaction
    @Query("SELECT * FROM Purchases WHERE Id = :id")
    fun select(id: Long): EverythingPurchase

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Purchases " +
            "INNER JOIN Purchase_Characters ON Purchase = Id " +
            "WHERE Character = :character")
    fun selectAllByCharacter(character: Long): List<Purchase>

    @Query("SELECT * FROM Purchases WHERE Seller = :seller")
    fun selectAllBySeller(seller: Long): List<Purchase>

    @Query("SELECT * FROM Purchases WHERE Event = :event")
    fun selectAllByEvent(event: Long): List<Purchase>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Purchases " +
            "INNER JOIN Purchase_Tags ON Purchase = Id " +
            "WHERE Tag = :tag")
    fun selectAllByTag(tag: Long): List<Purchase>

    @Transaction
    @Query("SELECT * FROM Purchases")
    fun selectAll(): List<PurchaseSellerEvent>

    @Transaction
    @Query("SELECT * FROM Purchases")
    fun pagingSource(): PagingSource<Int, PurchaseSellerEvent>

}