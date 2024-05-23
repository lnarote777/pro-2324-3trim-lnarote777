package graphicInterface

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import entity.CtfEntity
import entity.GroupEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.io.File

class GraphicInterface {

    @Composable
    fun Window(onclose: () -> Unit, groups: List<GroupEntity>, ctfs: List<CtfEntity>) {
        val file = File("ctfsgroups.txt")
        Window(
            onCloseRequest = onclose,
            title = "CTFs - IES Rafael Alberti"
        ){

            Content(groups, ctfs)
        }
    }

    @Composable
    fun Content(groups: List<GroupEntity>, ctfs: List<CtfEntity>){
        var groupDesc by remember { mutableStateOf("") }
        var groupList by remember { mutableStateOf(groups) }
        var text by remember { mutableStateOf("Filter") }


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Botones(
                    text = text ,
                    groupDesc = groupDesc,
                    onvalueChange = {groupDesc = it},
                    onClick1 = {
                        if (text == "Filter"){
                            groupList = filtrar(groups, groupDesc)
                            text = "Show All"
                        } else {
                            groupList = groups
                            text = "Filter"
                        }
                    },
                    onClick2 = { exportar(File("ctfsgroups.txt"), groups, ctfs) }
                )

                Spacer(modifier = Modifier.size(20.dp))
                if (groupDesc == ""){
                    groupList = groups
                }

                Listado(groupList)

            }

        }

    }

    @Composable
    fun Botones(text: String, groupDesc: String, onvalueChange: (String) -> Unit, onClick1: () -> Unit, onClick2: () -> Unit){

        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(
                onClick =  onClick1
            ){
                Text(text)
            }

            OutlinedTextField(
                value = groupDesc,
                onValueChange = onvalueChange,
                label = { Text("Group Description") }
            )

            Spacer(modifier = Modifier.size(60.dp))

            Button(
                onClick = onClick2
            ){
                Text("Exportar")
            }
        }

    }

    @Composable
    fun Listado(groups: List<GroupEntity>){
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(2.dp, Color.Black)
                .width(300.dp)
                .height(200.dp)

        ) {
            items(groups) { group ->
                Text("ID: ${group.groupId} Desc: ${group.groupDesc} Mejor Posicion Ctf: ${group.bestPosCtfId}")
            }
        }
    }

    private fun exportar(file: File, groups: List<GroupEntity>, ctfs: List<CtfEntity>){
        if (!file.exists()){
            file.createNewFile()
        }

        ctfs.forEach { ctf ->
            file.appendText("CTF -> ${ctf.ctfId}\n")
            groups.forEach { group ->
                if (ctf.groupId == group.groupId) file.appendText("${group.groupDesc} (${ctf.punctuation})\n")
            }
        }
    }

    private fun filtrar(groups: List<GroupEntity>, groupDesc: String): MutableList<GroupEntity> {
        val groupsDesc = mutableListOf<GroupEntity>()
        groups.forEach { group ->
            if (group.groupDesc == groupDesc){
                groupsDesc.add(group)
            }

        }
        return groupsDesc
    }
}

