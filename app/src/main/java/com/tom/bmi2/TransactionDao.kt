package com.tom.bmi2

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("select * from `Transaction` where id = :id ")
    fun  getById(id: Int) : Transaction
    @Query("select * from `Transaction` ORDER BY createAt DESC")
    fun  getAll() : List<Transaction>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tran: Transaction)
    @Delete
    fun  delete(tran: Transaction)
    @Update
    fun update(tran: Transaction)
}