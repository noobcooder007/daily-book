package com.bonsaisoftware.dailybook.model

import java.util.Date

data class Goal(
    val goalId: Long,
    val goalName: String,
    val goalAmount: Long,
    val goalDate: Date,
    val goalStatus: GoalStatus,
    val goalIsActive: Boolean
)

enum class GoalStatus(val status: String) {
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled")
}
