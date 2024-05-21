package output

import entity.CtfEntity
import entity.GroupEntity

interface IOutputInfo {
    fun showMessage(mensaje: String, salto: Boolean = true)
    fun showGroup(group: GroupEntity, ctf: CtfEntity)
    fun showGroups(group: GroupEntity)
    fun showCtf(ctf: CtfEntity, group: GroupEntity)
}