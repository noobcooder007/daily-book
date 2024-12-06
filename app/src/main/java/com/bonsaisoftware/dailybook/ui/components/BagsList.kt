package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bonsaisoftware.dailybook.presentation.BagsUiState
import com.bonsaisoftware.dailybook.util.currencyFormat

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BagsList(
    innerPadding: PaddingValues,
    uiState: BagsUiState,
    onItemClick: (bagId: Long) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            ),
        ) {
            SummaryCard(
                modifier = Modifier.sharedElement(
                    rememberSharedContentState(key = "summaryBags"),
                    animatedVisibilityScope
                ),
                total = currencyFormat(uiState.total.toBigDecimal()),
                label = "Total apartado",
                currency = "MXN",
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (uiState.bags.isEmpty()) {
                EmptyList()
            } else {
                LazyColumn {
                    items(uiState.bags) { bag ->
                        BagListItem(bag = bag, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun BagsListPreview() {
//    BagsList(
//        innerPadding = PaddingValues(0.dp),
//        uiState = BagsUiState(
//            bags = emptyList(),
//            total = 0
//        )
//    )
//}
