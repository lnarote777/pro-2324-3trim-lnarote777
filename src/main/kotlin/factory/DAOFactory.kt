package factory

import dao.ctfs.*
import dao.groups.*
import output.IOutputInfo
import javax.sql.DataSource

/**
 * A factory class to create instances of data access objects (DAOs) for different sources.
 * The factory supports multiple data source types such as SQL, JSON, TXT, and XML.
 */
class DAOFactory(private val dataSource: DataSource, private val console: IOutputInfo) {

    enum class DaoSourceType {
        SQL,
        JSON,
        TXT,
        XML
    }

    /**
     * Creates and returns an instance of [ICtfDAO] based on the specified [DaoSourceType].
     */
    fun getCtfDao(daoSourceType: DaoSourceType): ICtfDAO {
        return when (daoSourceType) {
            DaoSourceType.SQL -> CtfDAOH2(dataSource, console)
            DaoSourceType.JSON -> CtfDAOJSON(dataSource, console)
            DaoSourceType.TXT -> CtfDAOTXT(dataSource, console)
            DaoSourceType.XML -> CtfDAOXML(dataSource, console)
        }
    }

    /**
     * Creates and returns an instance of [IGroupDAO] based on the specified [DaoSourceType].
     */
    fun getGroupDao(daoSourceType: DaoSourceType): IGroupDAO {
        return when (daoSourceType) {
            DaoSourceType.SQL -> GroupDAOH2(dataSource, console)
            DaoSourceType.JSON -> GroupDAOJSON(dataSource, console)
            DaoSourceType.TXT -> GroupDAOTXT(dataSource, console)
            DaoSourceType.XML -> GroupDAOXML(dataSource, console)
        }
    }
}
