package services

import entity.GroupEntity


interface IGroupService {
    fun createGroup(groupDesc: String): String?
    fun getGroupById(id: Int): GroupEntity?
    fun updateGroup(ctfId: Int?, group: GroupEntity): GroupEntity?
    fun deleteGroup(id: Int): Boolean?
    fun getAllGroups(): List<GroupEntity>?
}