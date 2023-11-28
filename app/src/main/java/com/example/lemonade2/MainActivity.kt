package com.example.lemonade2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade2.ui.theme.Lemonade2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lemonade2Theme {
                // A surface container using the 'background' color from the theme
                    LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var currentState by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF000000),
                    titleContentColor = Color(0xFFFFF9E44C),
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentState) {
                1 -> {
                    LemonadeImageandText(textLabelReId = R.string.first_text,
                        drawableReId = R.drawable.lemon_tree,
                        contentDescriptionId = R.string.first_description,
                        onImgClick = {
                            currentState = 2
                            squeezeCount = (2..4).random()
                        })
                }
                2 -> {
                    LemonadeImageandText(textLabelReId = R.string.second_text,
                        drawableReId = R.drawable.lemon_squeeze,
                        contentDescriptionId = R.string.second_description,
                        onImgClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentState = 3
                            }
                        })
                }
                3 -> {
                    LemonadeImageandText(textLabelReId = R.string.third_text,
                        drawableReId = R.drawable.lemon_drink,
                        contentDescriptionId = R.string.third_description,
                        onImgClick = {
                            currentState = 4
                        })
                }
                4 -> {
                    LemonadeImageandText(textLabelReId = R.string.fourth_text,
                        drawableReId = R.drawable.lemon_restart,
                        contentDescriptionId = R.string.fourth_description,
                        onImgClick = {
                            currentState = 1
                        })
                }
            }
        }
    }
}

@Composable
fun LemonadeImageandText(modifier: Modifier = Modifier,
                         textLabelReId: Int,
                         drawableReId: Int,
                         contentDescriptionId: Int,
                         onImgClick: () -> Unit,)
{
Box(modifier = modifier){
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        Button(onClick = onImgClick,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(Color(0xFFC3ECD2)),
            border = BorderStroke(2.dp,Color(0xFFFFF9E44C))
        ) {
            Image(
                painter = painterResource(id = drawableReId),
                contentDescription = stringResource(id = contentDescriptionId),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height))
                    .padding(dimensionResource(R.dimen.button_interior_padding))

            )
        }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(text = stringResource(id = textLabelReId),
            fontSize = 18.sp)
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    Lemonade2Theme {
        LemonadeApp()
    }
}