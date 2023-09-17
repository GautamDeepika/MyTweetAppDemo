package com.example.mytweetappdemo.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mytweetappdemo.R
import com.example.mytweetappdemo.viewmodels.CategoryVM
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryVM: CategoryVM = hiltViewModel()

    val categories: State<List<String>> = categoryVM.categories.collectAsState()

//    Column {
//        Box(modifier = Modifier
//            .background( Color(90,98,113))
//            .fillMaxWidth(1f)){
//            Text(
//                text = "Home Page",
//                fontSize = 24.sp,
//                color = Color.White,
//                style = MaterialTheme.typography.labelLarge,
//                modifier = Modifier
//                    .padding(19.dp, 18.dp, 0.dp, 18.dp)
//                    .align(alignment = TopStart)
//            )
//        }
    if (categories.value.isEmpty()) {
        Loader()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, onClick)
            }
        }
    }
//    }

}

fun toTitleCase(input: String): String {
    return input.split(" ").joinToString(" ") { it.capitalize() }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    val formattedText = toTitleCase(category)
    Box(
        modifier = Modifier
            .padding(4.dp, 8.dp, 4.dp, 4.dp)
            .size(180.dp)
            .clickable { onClick(category) }
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(90, 98, 113))
            .paint(
                painter = painterResource(id = R.drawable.ic_cloudy_bg),
                contentScale = ContentScale.FillBounds
            )
            .then(
                Modifier.shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = false, spotColor = Color.LightGray, ambientColor = Color.LightGray
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = formattedText,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}




