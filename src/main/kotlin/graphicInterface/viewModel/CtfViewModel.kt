package graphicInterface.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class CtfViewModel: ICtfViewModel {

    private var _newCtf = mutableStateOf("")
    override val newCtf: State<String> = _newCtf


    override val ctfs: List<String>
        get() = TODO("Not yet implemented")
    override val selectedIndex: State<Int>
        get() = TODO("Not yet implemented")

    override fun addCtf() {
        TODO("Not yet implemented")
    }

    override fun showCtf() {
        TODO("Not yet implemented")
    }
}