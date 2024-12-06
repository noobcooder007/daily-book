package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonsaisoftware.dailybook.model.Expense
import com.bonsaisoftware.dailybook.model.ExpenseCategory
import java.util.Date
import kotlin.math.absoluteValue

@Composable
fun ExpenseForm(
    expense: Expense?,
    innerPadding: PaddingValues,
    onSaveChanges: (Expense) -> Unit
) {
    var amount by remember {
        mutableStateOf(
            expense?.expenseAmount?.absoluteValue ?: 0
        )
    }
    var name by remember { mutableStateOf(expense?.expenseName ?: "") }
    var category by remember {
        mutableStateOf(
            expense?.expenseCategory?.name ?: ExpenseCategory.OTHER.name
        )
    }
    var isExpense by remember { mutableStateOf(expense?.expenseIsAnExpense ?: true) }
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
                onValueChange = { name = it },
                placeholder = { Text("Type a description") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                enabled = if (expense?.expenseCanEdit != true) true else false
            )
            NumberFormField(
                label = "Amount",
                value = "$amount",
                onValueChange = { amount = it },
                enabled = if (expense?.expenseCanEdit != true) true else false
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = isExpense,
                    onCheckedChange = { isExpense = it },
                    enabled = if (expense?.expenseCanEdit != true) true else false
                )
                Text("Is a expense")
            }
            CustomDropdown(
                item = category,
                items = ExpenseCategory.entries.map { it.name },
                onItemSelect = { category = it },
                enabled = if (expense?.expenseCanEdit != true) true else false
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            FilledTonalIconButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = if (expense?.expenseCanEdit != true && name.isNotEmpty()) true else false,
                onClick = {
                    onSaveChanges(
                        Expense(
                            expenseId = expense?.expenseId ?: 0L,
                            expenseAmount = (if (isExpense) -amount else amount),
                            expenseName = name,
                            expenseDate = expense?.expenseDate ?: Date(),
                            expenseIsAnExpense = isExpense,
                            expenseCategory = ExpenseCategory.valueOf(category),
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
fun ExpenseNullFormPreview() {
    ExpenseForm(innerPadding = PaddingValues(), expense = null, onSaveChanges = {})
}

@Preview(showBackground = true)
@Composable
fun ExpenseFormPreview() {
    ExpenseForm(innerPadding = PaddingValues(), expense = Expense(
        expenseId = 1L,
        expenseAmount = 100,
        expenseName = "Test",
        expenseCategory = ExpenseCategory.FOOD,
        expenseDate = Date(),
        expenseIsActive = true
    ), onSaveChanges = {})
}
