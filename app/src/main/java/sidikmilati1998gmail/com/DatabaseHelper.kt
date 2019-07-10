package sidikmilati1998gmail.com

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, TABLE_NAME, null, 1) {

    val data: Cursor
        get() {
            val db = this.writableDatabase
            val query = "Select * from $TABLE_NAME"
            return db.rawQuery(query, null)
        }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Col2 + " TEXT)"
        sqLiteDatabase.execSQL(createTable)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(sqLiteDatabase)
    }

    fun addData(item: String): Boolean {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Col2, item)
        Log.d(TAG, "addData: adding " + item + "to" + TABLE_NAME)
        val result = database.insert(TABLE_NAME, null, contentValues)
        return if (result == 1L) {
            false
        } else {
            true
        }
    }

    companion object {
        private val TAG = "DatabaseHelper"
        private val TABLE_NAME = "people_table"
        private val Col1 = "ID"
        private val Col2 = "name"
    }
}
