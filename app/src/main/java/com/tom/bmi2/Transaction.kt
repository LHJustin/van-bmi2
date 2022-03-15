package com.tom.bmi2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Transaction(
    @PrimaryKey
    val id: Int,
    val account: String,
    @ColumnInfo(name = "createAt")
    val date: String,
    val amount: Int,
    val type: Int) {
}