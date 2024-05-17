package dao.groups

import entity.GroupEntity
import output.IOutputInfo
import javax.sql.DataSource

class GroupDAOTXT(private val dataSource: DataSource, private val console: IOutputInfo): IGroupDAO {
    override fun createGroup(groupDesc: String): String? {
        TODO("Not yet implemented")
    }

    override fun getGroupById(id: Int): GroupEntity? {
        TODO("Not yet implemented")
    }

    override fun updateGroup(group: GroupEntity): GroupEntity? {
        TODO("Not yet implemented")
    }

    override fun deleteGroup(id: Int): Boolean? {
        TODO("Not yet implemented")
    }

    override fun getAllGroups(): List<GroupEntity>? {
        TODO("Not yet implemented")
    }
}