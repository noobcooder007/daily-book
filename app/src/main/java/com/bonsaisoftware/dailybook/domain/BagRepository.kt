package com.bonsaisoftware.dailybook.domain

import com.bonsaisoftware.dailybook.model.Bag

interface BagRepository {
    fun getAllBags(): List<Bag>
    suspend fun addBag(bag: Bag)
    suspend fun editBag(bag: Bag)
}
