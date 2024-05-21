package graphicInterface.viewModel

import androidx.compose.runtime.State
import entity.CtfEntity

interface ICtfViewModel {

    val newCtf: State<String>
    val ctfs: List<CtfEntity>
    val selectedIndex: State<Int>
    val infoMessage: State<String>
    val showInfoMessage: State<Boolean>

    fun showCtf()
    fun infoMessage(info: String)
    fun mostrarInfo(show: Boolean)
}