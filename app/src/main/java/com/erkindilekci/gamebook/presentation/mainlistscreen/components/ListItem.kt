package com.erkindilekci.gamebook.presentation.mainlistscreen.components

import android.graphics.BitmapFactory
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.erkindilekci.gamebook.R
import com.erkindilekci.gamebook.domain.model.Game
import com.erkindilekci.gamebook.presentation.theme.CardColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun ListItemCard(game: Game) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clip(RoundedCornerShape(10.dp))
            .background(CardColor)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (!isExpanded) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = game.title,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )

                Icon(
                    painter = painterResource(id = R.drawable.outline_info_24),
                    contentDescription = "info",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(text = game.genre, fontSize = 16.sp, color = Color.White)
        } else {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(CardColor)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .width(300.dp)
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    var image by remember { mutableStateOf<ImageBitmap?>(null) }
                    var isLoading by remember { mutableStateOf(false) }

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                                .align(Alignment.Center),
                            color = Color.White
                        )
                    }

                    if (image != null) {
                        Image(
                            bitmap = image!!,
                            contentDescription = game.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    LaunchedEffect(key1 = game.thumbnail) {
                        isLoading = true
                        val imageData = withContext(Dispatchers.IO) {
                            try {
                                URL(game.thumbnail).openStream().use {
                                    BitmapFactory.decodeStream(it)
                                }
                            } catch (e: Exception) {
                                null
                            }
                        }

                        if (imageData != null) {
                            image = imageData.asImageBitmap()
                        }
                        isLoading = false
                    }
                }


                Text(text = game.title, fontSize = 18.sp, color = Color.White)
                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = game.shortDescription,
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Genre: ${game.genre}", fontSize = 16.sp, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Platform: ${game.platform}", fontSize = 16.sp, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Publisher: ${game.publisher}", fontSize = 16.sp, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Developer: ${game.developer}", fontSize = 16.sp, color = Color.White)
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Release Date: ${game.release_date}",
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
