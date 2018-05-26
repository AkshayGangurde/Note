package com.akshay.notes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Created by Akshay on 18-May-18.
 */
class DBSchema(private val mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "notes.db"

        val TABLE_NOTES = "notes"

        private val COMMA_SPACE = ", "
        private val CREATE_TABLE = "CREATE TABLE "
        private val PRIMARY_KEY = "PRIMARY KEY "
        private val UNIQUE = "UNIQUE "
        private val TYPE_TEXT = " TEXT "
        private val TYPE_DATE = " DATETIME "
        private val TYPE_INT = " INTEGER "
        private val DEFAULT = "DEFAULT "
        private val AUTOINCREMENT = "AUTOINCREMENT "
        private val NOT_NULL = "NOT NULL "
        private val DROP_TABLE = "DROP TABLE IF EXISTS "
    }

    object TB_NOTES {
        val ID = "_id"
        val NOTE = "note"
        val DATE = "date"
    }

    private val CREATE_TABLE_NOTES = CREATE_TABLE + TABLE_NOTES + " ( " +
            TB_NOTES.ID + TYPE_INT + NOT_NULL + PRIMARY_KEY + COMMA_SPACE +
            TB_NOTES.NOTE + TYPE_DATE + NOT_NULL + COMMA_SPACE +
            TB_NOTES.DATE + TYPE_TEXT + NOT_NULL +
            ")"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_NOTES);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(CREATE_TABLE);
    }
}