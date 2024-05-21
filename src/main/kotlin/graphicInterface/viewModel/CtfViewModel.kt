package graphicInterface.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import entity.CtfEntity
import services.CtfServiceImpl

class CtfViewModel(private val ctfServiceImpl: CtfServiceImpl): ICtfViewModel {

    private var _newCtf = mutableStateOf("")
    override val newCtf: State<String> = _newCtf

    private var _ctfs = mutableStateListOf<CtfEntity>()
    override val ctfs: List<CtfEntity> = _ctfs

    private var _selectedIndex = mutableStateOf(-1)
    override val selectedIndex: State<Int> = _selectedIndex

    private var _info = mutableStateOf("")
    override val infoMessage: State<String> = _info

    private var _show = mutableStateOf(false)
    override val showInfoMessage: State<Boolean> = _show



    override fun showCtf() {
        val ctfs = ctfServiceImpl.getAllCtf()

        if (ctfs != null){
            for (ctf in ctfs){
                _ctfs.add(ctf)
            }
        }else{
            infoMessage("Empty data base")
        }
    }

    override fun infoMessage(info: String) {
        _info.value = info
        mostrarInfo(true)
    }

    override fun mostrarInfo(show: Boolean) {
        _show.value = show
    }
}