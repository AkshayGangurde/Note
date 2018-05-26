package com.akshay.notes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.akshay.notes.models.Note


/**
 * Created by Akshay on 18-May-18.
 */
class DAO(private val mContext: Context) {

    private var mHelper: DBSchema = DBSchema(mContext)

    //SELECTION
    companion object {
        private val SELECT_ID_BASED = DBSchema.TB_NOTES.ID + " = ? "
        private val PROJECTION_ALL = " * "
        val SORT_ORDER_DEFAULT = DBSchema.TB_NOTES.ID + " DESC"
    }

    private fun getReadDB(): SQLiteDatabase {
        return mHelper.readableDatabase
    }

    private fun getWriteDB(): SQLiteDatabase {
        return mHelper.writableDatabase
    }

    fun insertNote(note: Note): Note? {
        val db = getWriteDB()
        val id = db.insert(DBSchema.TABLE_NOTES, null, note.getValues())
        val insertedNote = getNote(id.toInt())
        db.close()
        return insertedNote
    }

    fun deleteNote(note: Note): Long {
        val db = getWriteDB()
        val res = db.delete(
                DBSchema.TABLE_NOTES,
                SELECT_ID_BASED,
                arrayOf(Integer.toString(note.getId()))
        ).toLong()
        db.close()
        return res
    }

    fun getAllNotes(): ArrayList<Note>? {
        val db = getReadDB()
        val cursor = db.query(
                DBSchema.TABLE_NOTES,
                null, null, null, null, null,
                SORT_ORDER_DEFAULT
        )

        if (cursor != null) {
            cursor.moveToFirst()
            val notes = ArrayList<Note>()
            while (!cursor.isAfterLast) {
                val note = Note(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DBSchema.TB_NOTES.ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.TB_NOTES.NOTE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.TB_NOTES.DATE))
                )
                notes.add(note)
                cursor.moveToNext()
            }
            cursor.close()
            db.close()
            return notes
        }
        return null
    }

    fun getNote(id: Int): Note? {
        val db = getReadDB()
        val cursor = db.query(
                DBSchema.TABLE_NOTES,
                null,
                SELECT_ID_BASED,
                arrayOf(Integer.toString(id)), null, null, null
        )
        if (cursor != null) {
            cursor.moveToFirst()
            val note = Note(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DBSchema.TB_NOTES.ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.TB_NOTES.NOTE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBSchema.TB_NOTES.DATE))
            )
            cursor.close()
            db.close()
            return note
        }
        return null
    }
}