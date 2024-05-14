package services

import dao.GroupDAOH2
import entity.GroupEntity

class GroupServiceImpl(private val groupDAO: GroupDAOH2): IGroupService {
    override fun createGroup(group: GroupEntity): GroupEntity? {
        return groupDAO.createGroup(group)
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