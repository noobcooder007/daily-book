package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonsaisoftware.dailybook.data.BagManager
import com.bonsaisoftware.dailybook.presentation.BagsUiState
import com.bonsaisoftware.dailybook.util.currencyFormat

@Composable
fun BagsList(innerPadding: PaddingValues, uiState: BagsUiState) {
    LazyColumn(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding()
        ),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        item {
            SummaryCard(
                total = currencyFormat( "${uiState.total}"),
                label = "Balance total",
                currency = "MXN",
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(uiState.bags) { bag ->
            BagListItem(bag = bag)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BagsListPreview() {
    BagsList(
        innerPadding = PaddingValues(0.dp),
        uiState = BagsUiState(
            bags = BagManager.bags,
            total = BagManager.bags.sumOf { it.bagAmount }
        )
    )
}
