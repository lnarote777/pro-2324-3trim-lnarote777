package services

import dao.groups.GroupDAOH2
import dao.groups.IGroupDAO
import entity.GroupEntity

class GroupServiceImpl(private val groupDAO: IGroupDAO): IGroupService {
    override fun createGroup(groupDesc: String): String? {
        return groupDAO.createGroup(groupDesc)
    }

    override fun getGroupById(id: Int): GroupEntity? {
        return groupDAO.getGroupById(id)
    }

    override fun updateGroup(group: GroupEntity): GroupEntity? {
        return groupDAO.updateGroup(group)
    }

    override fun deleteGroup(id: Int): Boolean? {
        return groupDAO.deleteGroup(id)
    }

    override fun getAllGroups(): List<GroupEntity>? {
        return groupDAO.getAllGroups()
    }
}