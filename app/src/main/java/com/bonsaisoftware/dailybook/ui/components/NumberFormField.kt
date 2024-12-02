package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.bonsaisoftware.dailybook.util.CurrencyAmountInputVisualTransformation

@Composable
fun NumberFormField(
    label: String,
    value: String,
    onValueChange: (Long) -> Unit,
    enabled: Boolean = true
) {
    var text by remember { mutableStateOf(value) }
    OutlinedTextField(
        label = { Text(label) },
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(),
        value = text,
        prefix = { Text("\$") },
        onValueChange = {
            text = if (it.startsWith("0")) {
                ""
            } else {
                it
            }
            if (text.isNotEmpty()) onValueChange(text.toLong())
        },
        visualTransformation = CurrencyAmountInputVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        placeholder = { Text("Ingresa una cantidad") }
    )
}

@Preview(showBackground = true)
@Composable
fun NumberFormFieldPreview() {
    NumberFormField(label = "Amount", value = "0", onValueChange = {})
}
