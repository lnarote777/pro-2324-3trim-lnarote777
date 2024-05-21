package graphicInterface

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication



    fun main() = application {
        Window(
            onCloseRequest = { exitApplication() },
            title = "CTFs - IES Rafael Alberti"
        ){
            Content()
        }
    }

    @Composable
    @Preview
    fun Content(){

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {

            Row (horizontalArrangement = Arrangement.Center){
                Botones()
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .border(2.dp, Color.Black)
                    .width(10.dp)
                    .height(20.dp)

            ) {
                Box(
                    modifier = Modifier.size(10.dp)
                )
                Box(
                    modifier = Modifier.size(10.dp)
                )
            }
        }
    }

@Composable
fun Botones(){
    Button(
        onClick = { TODO() }
    ){
        Text("Holi")
    }
    Button(
        onClick = { TODO() }
    ){
        Text("Holi2")
    }
}
