package dao.ctfs

import entity.CtfEntity

interface ICtfDAO {
    fun createCtf(ctf: CtfEntity): CtfEntity?
    fun getCtfById(id: Int): List<CtfEntity>?
    fun updateCtf(ctf: CtfEntity): CtfEntity?
    fun deleteCtf(id: Int): Boolean?
    fun getAllCtf(): List<CtfEntity>?
}