package graphicInterface.viewModel

import androidx.compose.runtime.State

interface ICtfViewModel {

    val newCtf: State<String>
    val ctfs: List<String>
    val selectedIndex: State<Int>

    fun addCtf()
    fun showCtf()


}