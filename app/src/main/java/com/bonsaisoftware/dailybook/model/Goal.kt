package com.bonsaisoftware.dailybook.model

data class Goal(
    val goalId: Long,
    val goalName: String,
    val goalAmount: Long,
    val goalDate: String,
    val goalStatus: GoalStatus
)

enum class GoalStatus(val status: String) {
    PENDING("Pending"),
    COMPLETED("Completed"),
}
