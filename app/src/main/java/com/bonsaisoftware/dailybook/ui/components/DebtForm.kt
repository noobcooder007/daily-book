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
import androidx.compose.runtime.mutableLongStateOf
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
import com.bonsaisoftware.dailybook.model.CreditCard
import com.bonsaisoftware.dailybook.model.Debt
import java.util.Date
import kotlin.math.absoluteValue

@Composable
fun DebtForm(
    debt: Debt?,
    innerPadding: PaddingValues,
    onSaveChanges: (Debt) -> Unit
) {
    var name by remember { mutableStateOf(debt?.debtName ?: "") }
    var amount by remember { mutableLongStateOf(debt?.debtAmount?.absoluteValue ?: 0) }
    var creditCard by remember {
        mutableStateOf(
            debt?.debtCreditCard?.name ?: CreditCard.OTHER.name
        )
    }
    var isAPayment by remember { mutableStateOf(debt?.debtIsAPayment ?: false) }
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
                )
            )
            NumberFormField(
                label = "Amount",
                value = "$amount",
                onValueChange = { amount = it },
                enabled = if (debt?.debtIsAPayment == true) false else true,
            )
            CustomDropdown(
                item = creditCard,
                items = CreditCard.entries.map { it.name },
                onItemSelect = { creditCard = it },
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isAPayment,
                    onCheckedChange = { isAPayment = it },
                    enabled = if (debt?.debtIsAPayment == true) false else true
                )
                Text("Is a payment")
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            FilledTonalIconButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = name.isNotEmpty(),
                onClick = {
                    onSaveChanges(
                        Debt(
                            debtId = debt?.debtId ?: 0L,
                            debtName = name,
                            debtAmount = if (isAPayment) -amount else amount,
                            debtCreditCard = CreditCard.valueOf(creditCard),
                            debtIsActive = true,
                            debtDate = Date(),
                            debtIsAPayment = isAPayment
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
fun DebtNullFormPreview() {
    DebtForm(
        debt = null,
        innerPadding = PaddingValues(),
        onSaveChanges = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DebtFormPreview() {
    DebtForm(
        debt = Debt(
            debtId = 1L,
            debtName = "Test",
            debtAmount = 10000,
            debtIsActive = true,
            debtDate = Date(),
            debtCreditCard = CreditCard.BBVA,
            debtIsAPayment = false
        ),
        innerPadding = PaddingValues(),
        onSaveChanges = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DebtWithPaymentFormPreview() {
    DebtForm(
        debt = Debt(
            debtId = 1L,
            debtName = "Test",
            debtAmount = -10000,
            debtIsActive = true,
            debtDate = Date(),
            debtCreditCard = CreditCard.BBVA,
            debtIsAPayment = true
        ),
        innerPadding = PaddingValues(),
        onSaveChanges = {}
    )
}
