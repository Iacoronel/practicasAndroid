package com.example.loginapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val DROP_TABLE_USER = "DROP TABLE IF EXISTS $TABLE_USER"

    private val CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER (" +
            "$COL_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COL_USER_NAME TEXT, " +
            "$COL_USER_EMAIL TEXT, " +
            "$COL_USER_PASSWORD TEXT)"

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TABLE_USER = "tbl_user"

        private const val COL_USER_ID = "user_id"
        private const val COL_USER_NAME = "user_name"
        private const val COL_USER_EMAIL = "user_email"
        private const val COL_USER_PASSWORD = "user_password"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_TABLE_USER)
    }

    fun registraUsuario(user: User){
        val db = this.writableDatabase
        val value = ContentValues()

        value.put(COL_USER_NAME, user.username)
        value.put(COL_USER_EMAIL, user.email)
        value.put(COL_USER_PASSWORD, user.password)

        db.insert(TABLE_USER, null, value)
        db.close()
    }

    /**
     *  Realizado 01/03/2024
     */

    fun accesoUsuario(email: String, password: String): Boolean{
        val columns = arrayOf(COL_USER_ID)
        val db = this.readableDatabase
        val selection = "$COL_USER_EMAIL = ? AND $COL_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        return cursorCount > 0
    }

    //Realizado 01-03-2024
    fun regresaCuentas(): List<User> {
        val columns = arrayOf(COL_USER_ID, COL_USER_EMAIL, COL_USER_NAME, COL_USER_PASSWORD)
        val sortOrder = "$COL_USER_NAME ASC"
        val userList = ArrayList<User>()

        val db = this.readableDatabase
        val cursor = db.query(TABLE_USER,
            columns,
            null,
            null,
            null,
            null,
            sortOrder)

        if (cursor.moveToFirst()){
            do{
                val user = User(
                    id = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_ID)).toInt(),
                    username = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_NAME)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_EMAIL)),
                    password = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_PASSWORD)),
                )
                userList.add(user)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }



}