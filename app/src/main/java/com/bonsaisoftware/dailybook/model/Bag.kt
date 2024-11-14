package com.bonsaisoftware.dailybook.model

data class Bag(
    val bagId: Long,
    val bagName: String,
    val bagAmount: Long,
    val bagDate: String,
    val goalId: Long? = null,
)
