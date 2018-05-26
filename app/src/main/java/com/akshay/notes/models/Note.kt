package com.akshay.notes.models

import android.content.ContentValues
import com.akshay.notes.data.DBSchema

/**
 * Created by Akshay on 18-May-18.
 */
data class Note(private var id: Int = -1, private var mText: String = "", private var mDate: String = "") {


    fun getValues(): ContentValues? {
        val contentValues = ContentValues()
        if (id != -1) {
            contentValues.put(DBSchema.TB_NOTES.ID, id)
            contentValues.put(DBSchema.TB_NOTES.NOTE, mText)
            contentValues.put(DBSchema.TB_NOTES.DATE, mDate)
        }
        return contentValues
    }

    fun getId(): Int {
        return id
    }

    fun getNote(): String {
        return mText
    }

    fun getDate(): String {
        return mDate
    }
}