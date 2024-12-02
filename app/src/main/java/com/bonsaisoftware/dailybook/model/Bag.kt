package com.bonsaisoftware.dailybook.model

import java.util.Date

data class Bag(
    val bagId: Long,
    val bagName: String,
    val bagAmount: Long,
    val bagDate: Date,
    val bagIsActive: Boolean,
    val goal: Goal? = null,
)
