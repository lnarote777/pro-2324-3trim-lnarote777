package factory

import dao.ctfs.*
import dao.groups.GroupDAOH2
import dao.groups.GroupDAOJSON
import dao.groups.GroupDAOTXT
import dao.groups.IGroupDAO
import output.IOutputInfo
import javax.sql.DataSource

class DAOFactory(private val dataSource: DataSource, private val console: IOutputInfo) {

    enum class DaoSourceType {
        SQL,
        JSON,
        TXT,
        XML
    }

    fun getCtfDao(daoSourceType: DaoSourceType): ICtfDAO {
        return when (daoSourceType) {
            DaoSourceType.SQL -> CtfDAOH2(dataSource, console)
            DaoSourceType.JSON -> CtfDAOJSON(dataSource, console)
            DaoSourceType.TXT -> CtfDAOTXT(dataSource, console)
            DaoSourceType.XML -> CtfDAOXML(dataSource, console)
        }
    }

    fun getGroupDao(daoSourceType: DaoSourceType): IGroupDAO {
        return when (daoSourceType) {
            DaoSourceType.SQL -> GroupDAOH2(dataSource, console)
            DaoSourceType.JSON -> GroupDAOJSON(dataSource, console)
            DaoSourceType.TXT -> GroupDAOTXT(dataSource, console)
            DaoSourceType.XML -> GroupDAOH2(dataSource, console)
        }
    }
}
