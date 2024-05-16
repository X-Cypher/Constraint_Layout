package com.example.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.Dimension
import com.example.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintLayoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

@Composable
fun ConstraintLayout(){
        androidx.constraintlayout.compose.ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (redButton, greenButton, blueButton, blackButton) = createRefs()

            Button(onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier.constrainAs(redButton) {//assign the button to the redButton variable
                    top.linkTo(parent.top) //top of the button is connected to the top of the parent
                    height = Dimension.value(100.dp)
                    width = Dimension.matchParent
                }
            ) { Text(text = "Red") }

            Button(onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Green),
                modifier = Modifier.constrainAs(greenButton) {//assign the button to the greenButton variable
                    top.linkTo(redButton.bottom) //top of the button is connected to the bottom of the redButton
                }
            ) { Text(text = "Green") }

            Button(onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Blue),
                modifier = Modifier.constrainAs(blueButton) {//assign the button to the blueButton variable
                    top.linkTo(redButton.bottom) //top of the button is connected to the bottom of the redButton
                }
            ) { Text(text = "Blue") }

            createHorizontalChain(greenButton, blueButton, chainStyle = ChainStyle.Spread) //spread the buttons horizontally, without this the buttons were overlapping

            val guideline = createGuidelineFromBottom(20.dp) //create an invisible guideline from the bottom of the screen)

            Button(onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.constrainAs(blackButton) { //assign the button to the blackButton variable
//                    top.linkTo(blueButton.bottom) //top of the button is connected to the bottom of the blueButton
                    bottom.linkTo(guideline)
                }
            ) { Text(text = "Black") }
        }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayoutTheme {
        ConstraintLayout()
    }
}