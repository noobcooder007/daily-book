package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonsaisoftware.dailybook.model.Goal
import com.bonsaisoftware.dailybook.model.GoalStatus
import java.util.Date

@Composable
fun GoalForm(innerPadding: PaddingValues, goal: Goal?, onSaveChanges: (Goal) -> Unit) {
    var name by remember { mutableStateOf(goal?.goalName ?: "") }
    var amount by remember { mutableStateOf(goal?.goalAmount ?: 0) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedTextField(
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it.trim() },
                placeholder = { Text("Type a description") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            NumberFormField(
                label = "Amount",
                value = "$amount",
                onValueChange = { amount = it }
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            FilledTonalButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotEmpty(),
                onClick = {
                    onSaveChanges(
                        Goal(
                            goalId = goal?.goalId ?: 0L,
                            goalName = name,
                            goalAmount = amount,
                            goalIsActive = true,
                            goalDate = goal?.goalDate ?: Date(),
                            goalStatus = GoalStatus.PENDING
                        )
                    )
                }
            ) {
                Text("Save")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalEmptyFormPreview() {
    GoalForm(innerPadding = PaddingValues(), goal = null, onSaveChanges = {})
}

@Preview(showBackground = true)
@Composable
fun GoalFormPreview() {
    GoalForm(
        innerPadding = PaddingValues(),
        goal = Goal(
            goalId = 1L,
            goalName = "Test",
            goalAmount = 100,
            goalIsActive = true,
            goalDate = Date(),
            goalStatus = GoalStatus.PENDING
        ), onSaveChanges = {})
}
