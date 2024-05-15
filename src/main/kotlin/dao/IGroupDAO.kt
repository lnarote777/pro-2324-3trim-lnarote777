package dao

import entity.GroupEntity

interface IGroupDAO {
    fun createGroup(groupDesc: String): String?
    fun getGroupById(id: Int): GroupEntity?
    fun updateGroup(group: GroupEntity): GroupEntity?
    fun deleteGroup(id: Int): Boolean?
    fun getAllGroups(): List<GroupEntity>?
}