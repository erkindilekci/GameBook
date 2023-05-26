package com.erkindilekci.gamebook.presentation.mainlistscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.gamebook.R
import com.erkindilekci.gamebook.presentation.theme.AppBar
import com.erkindilekci.gamebook.presentation.theme.BackGround

@Composable
fun MyAppBar(
    searchTf: String,
    onStringChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onFilterClicked: (String) -> Unit
) {
    var showSearchBar by remember { mutableStateOf(false) }
    var showFilterBar by remember { mutableStateOf(false) }

    if (showSearchBar) {
        TopAppBar(
            title = {
                TextField(
                    value = searchTf,
                    onValueChange = { onStringChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppBar)
                        .padding(horizontal = 8.dp),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search),
                            color = Color.White.copy(alpha = ContentAlpha.medium)
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = AppBar,
                        textColor = Color.White,
                        cursorColor = Color.White,
                        placeholderColor = Color.White.copy(alpha = ContentAlpha.medium),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    leadingIcon = {
                        IconButton(
                            onClick = { },
                            modifier = Modifier.alpha(ContentAlpha.disabled)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = stringResource(id = R.string.search),
                                tint = Color.White
                            )
                        }
                    }

                )
            },
            backgroundColor = AppBar,
            contentColor = Color.White,
            actions = {
                IconButton(onClick = {
                    showSearchBar = false
                    onCloseClicked()
                }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Close Search",
                        tint = Color.White
                    )
                }
            }
        )
    } else if (showFilterBar) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "Game Book",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                },
                backgroundColor = Color(0xFF0075d5),
                contentColor = Color.White,
                actions = {
                    IconButton(onClick = { showFilterBar = !showFilterBar }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = "Filter",
                            tint = Color.White
                        )
                    }

                    IconButton(onClick = { showSearchBar = true }) {
                        Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.White)
                    }
                }
            )

            FilterBar(onFilterClicked)
        }
    } else {
        TopAppBar(
            title = {
                Text(
                    text = "Game Book",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            },
            backgroundColor = Color(0xFF0075d5),
            contentColor = Color.White,
            actions = {
                IconButton(onClick = { showFilterBar = !showFilterBar }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter",
                        tint = Color.White
                    )
                }

                IconButton(onClick = { showSearchBar = true }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.White)
                }
            }
        )
    }
}

@Composable
fun FilterBar(
    onFilterClicked: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .height(45.dp)
            .background(BackGround)
    ) {
        FilterMenu(onFilterClicked)
    }
}

@Composable
fun FilterMenu(
    onFilterClicked: (String) -> Unit
) {
    val filters = listOf(
        "All", "Shooter", "MMOARPG", "ARPG", "Strategy", "MMORPG",
        "Fighing", "Action RPG", "Battle Royale", "MOBA",
        "Racing", "Card Game", "Sports", "MMO", "Social", "Fantasy"
    )

    LazyRow(
        contentPadding = PaddingValues(start = 6.dp, end = 6.dp, top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filters) { filter ->
            TextButton(
                onClick = { onFilterClicked(filter) },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = AppBar,
                    contentColor = Color.White
                )
            ) {
                Text(text = filter, color = Color.White)
            }
        }
    }
}
