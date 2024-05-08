package eu.tutorials.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tutorials.bmi.ui.theme.BMITheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    BMICalculation()

                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculation(){

    var inputHeight by remember { mutableStateOf("") }
    var inputWeight by remember { mutableStateOf("") }
    var BMI by remember { mutableStateOf("") }
    var BMIOutputValue by remember { mutableStateOf("") }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )

    fun BMIResult(){
        val inputHeightDouble = inputHeight.toDoubleOrNull() ?: 0.0
        val inputWeightDouble = inputWeight.toDoubleOrNull() ?: 0.0
        var BMIOutputValue = inputWeightDouble / (inputHeightDouble * inputHeightDouble).roundToInt()

        if (BMIOutputValue < 18.5) {
            BMI = "저체중"
        } else if (BMIOutputValue >= 18.5 && BMIOutputValue < 23) {
            BMI = "정상체중"
        } else if (BMIOutputValue >= 23 && BMIOutputValue < 25) {
            BMI = "과체중"
        } else if (BMIOutputValue >= 25 && BMIOutputValue < 30) {
            BMI = "경도비만"
        } else {
            BMI = "고도비만"
        }
//        BMIOutputValue = BMIOutputValue.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("BMI Calculator", style = customTextStyle)

        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = inputHeight,
            onValueChange = {
                inputHeight = it
            },
            label = {Text("Height")})
        OutlinedTextField(
            value = inputWeight,
            onValueChange = {
                inputWeight = it
            },
            label = {Text("Weight")})
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(onClick = {
            BMIResult()
        /*TODO*/ })
        {
            Text(text = "Calculation")
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        Text ("Result : $BMIOutputValue $BMI",
                style = MaterialTheme.typography.headlineMedium
        )
        
    }
}

@Preview(showBackground = true)
@Composable
fun BMIPreview(){
    BMICalculation()
}