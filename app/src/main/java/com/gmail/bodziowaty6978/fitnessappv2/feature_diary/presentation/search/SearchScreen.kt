package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Beige1
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.componens.SearchButton
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.componens.SearchProductItem
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.componens.SearchTopSection

@Composable
fun SearchScreen(
    mealName: String,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchBarState = viewModel.searchBarState.value
    val searchState = viewModel.searchState.value

    LaunchedEffect(key1 = true) {
        viewModel.initializeHistory()
    }



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(SearchEvent.ClickedSearch(searchBarState.text))
                },
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search"
                )
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchTopSection(
                searchState = searchBarState,
                mealName = mealName,
                date = CurrentDate.dateModel(LocalContext.current).valueToDisplay
                    ?: CurrentDate.dateModel(LocalContext.current).date,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                SearchButton(
                    text = stringResource(id = R.string.scan),
                    modifier = Modifier.weight(1F),
                    color = Beige1,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.barcode_scan),
                            contentDescription = stringResource(id = R.string.scan),
                            tint = Color.Black
                        )
                    },

                    )

                SearchButton(
                    text = stringResource(id = R.string.filter),
                    modifier = Modifier.weight(1F),
                    color = MaterialTheme.colors.secondary,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.filter),
                            contentDescription = stringResource(id = R.string.filter),
                            tint = Color.Black
                        )
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            if (searchState is SearchState.Success){

                val items = searchState.products

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    items(items.size){
                        SearchProductItem(product = items[it].product) {
                            viewModel.onEvent(SearchEvent.ClickedSearchItem(items[it]))
                        }
                    }
                }
            }
//            else{
//                Text(
//                    text = stringResource(id = R.string.searching_for_products),
//                    style = MaterialTheme.typography.body2,
//                    modifier = Modifier
//                        .padding(start = 20.dp)
//                )
//            }
        }
    }
}