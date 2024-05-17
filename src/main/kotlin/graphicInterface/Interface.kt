package graphicInterface

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.singleWindowApplication

@Composable
fun Window() = application{
    Window(
        onCloseRequest = { exitApplication() },
        title = "CTFs - IES Rafael Alberti"
    ){
        Content()
    }
}

@Composable
fun Content(){
    Surface {

    }
}