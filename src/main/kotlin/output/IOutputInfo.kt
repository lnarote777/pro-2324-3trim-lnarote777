package output

import entity.CtfEntity
import entity.GroupEntity
import javax.swing.text.Position

interface IOutputInfo {
    fun showMessage(mensaje: String, salto: Boolean = true)
    fun showGroup(group: GroupEntity, ctf: CtfEntity, position: Int)
    fun showGroups(group: GroupEntity)
    fun showCtf(ctf: CtfEntity, group: GroupEntity)
}