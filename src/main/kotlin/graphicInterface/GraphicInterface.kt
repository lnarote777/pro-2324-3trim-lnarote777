package graphicInterface

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import entity.CtfEntity
import java.io.File


fun main() = application {
    val file = File("ctfsgroups.txt")
    Window(
        onCloseRequest = { exitApplication() },
        title = "CTFs - IES Rafael Alberti"
    ){
        //LeerArchivo(file)
        Content()
    }
}

@Composable
@Preview
fun Content(){
    val groups = remember { mutableStateListOf<String>() }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Botones({ TODO() }, { TODO() })

            Spacer(modifier = Modifier.size(20.dp))

            Listado(groups)
        }

    }
}

@Composable

fun Botones(onClick1: () -> Unit, onClick2: () -> Unit){
    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = onClick1
        ){
            Text("Filtrar")
        }

        Spacer(modifier = Modifier.size(60.dp))

        Button(
            onClick = onClick2
        ){
            Text("Exportar")
        }
    }

}

@Composable
fun Listado(groups: MutableList<String>){
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(2.dp, Color.Black)
            .width(500.dp)
            .height(300.dp)

    ) {
        items(groups){ group ->


        }
    }
}

@Composable
fun LeerArchivo(file: File, ctfs: MutableList<String>){
    if (file.exists()){
        file.forEachLine { ctfs.add(it) }
    }else{
        file.createNewFile()
    }
}
