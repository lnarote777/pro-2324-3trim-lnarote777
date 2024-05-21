package dao.ctfs

import entity.CtfEntity

interface ICtfDAO {
    fun createCtf(ctf: CtfEntity): CtfEntity?
    fun getCtfById(id: Int): List<CtfEntity>?
    fun updateCtf(ctf: CtfEntity): CtfEntity?
    fun getAllCtf(): List<CtfEntity>?
    fun deleteCtfByGroupId(id: Int): Boolean?
    fun deleteCtf(id: Int): Boolean?
}