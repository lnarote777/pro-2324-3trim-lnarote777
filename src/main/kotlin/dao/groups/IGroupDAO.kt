package dao.groups

import entity.GroupEntity

interface IGroupDAO {
    fun createGroup(groupDesc: String): String?
    fun getGroupById(id: Int): GroupEntity?
    fun deleteGroup(id: Int): Boolean?
    fun getAllGroups(): List<GroupEntity>?
    fun updateGroup(ctfId: Int, group: GroupEntity): GroupEntity?
}