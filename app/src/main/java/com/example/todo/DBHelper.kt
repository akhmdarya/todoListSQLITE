package com.example.todo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.todo.ToDos.ToDo
import com.example.todo.ToDos.ToDoItem

class DBHelper(val context:Context ) :SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION ){
    override fun onCreate(db: SQLiteDatabase) {
       val createToDoTable = "CREATE TABLE $TABLE_TODO (" +
               "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
               "  $COL_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP," +
               "  $COL_NAME TEXT" +
               ");" +
               "" ;

//        val createToDoItemTable =
//        "CREATE TABLE $TABLE_TODO_ITEM (" +
//               "$COL_ITEM_ID integer PRIMARY KEY AUTOINCREMENT," +
//               "  $COL_ITEM_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP," +
//               "  $COL_TODO_ID  INTEGER," +
//               "  $COL_ITEM_NAME varchar," +
//               "  $COL_ITEM_IS_COMPLETED integer" +
//        ");" +
//        "" ;

        db.execSQL(createToDoTable)
      //  db.execSQL(createToDoItemTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


   fun addToDo(ToDo : ToDo) :Boolean{
       var db = writableDatabase
       var contentValues = ContentValues()
       contentValues.put(COL_NAME,ToDo.name)

       val result = db.insert(TABLE_TODO,null,contentValues)
       print("!!!!!!!!")
       if(result!=(-1).toLong())
       return true
       else return false
    }

//    fun addToDoItem(item:ToDoItem):Boolean{
//        var db = writableDatabase
//        var contentValues = ContentValues()
//        contentValues.put(COL_ITEM_NAME,item.nameItem)
//        contentValues.put(COL_TODO_ID, item.toDoId)
//        //contentValues.put(COL_ITEM_ID,item.idItem)
//        if(item.isCompleted)
//            contentValues.put(COL_ITEM_IS_COMPLETED,true)  //1
//        else
//            contentValues.put(COL_ITEM_IS_COMPLETED,false) //0
//
//
//        val result = db.insert(TABLE_TODO_ITEM,null,contentValues)
//        print("!!!!!!!!")
//        if(result!=(-1).toLong())
//            return true
//        else return false
//    }

   // @SuppressLint("Range")
    fun getToDos():MutableList<ToDo>
    {

        val result:MutableList<ToDo> = ArrayList()

        val db = this.readableDatabase
        val query = db.rawQuery(" SELECT * FROM $TABLE_TODO", null)
       if ( query.moveToFirst()){
           do {
               val todo= ToDo()
              todo.id=query.getLong(query.getColumnIndexOrThrow(COL_ID))
               todo.name=query.getString(query.getColumnIndexOrThrow(COL_NAME))
               //todo.createdAt=query.getString(query.getColumnIndex(COL_CREATED_AT))
              result.add(todo)
           }
               while (query.moveToNext())


       }
        query.close()
        return  result
    }

//    fun getToDoItems(toDoId:Long): MutableList<ToDoItem>
//    {
//
//        val result:MutableList<ToDoItem> = ArrayList()
//
//        val db = this.readableDatabase
//        val query = db.rawQuery(" SELECT * FROM $TABLE_TODO_ITEM WHERE $COL_TODO_ID=$toDoId ", null)
//        if ( query.moveToFirst()){
//            do {
//                val item= ToDoItem()
//                item.idItem=query.getLong(query.getColumnIndexOrThrow(COL_ITEM_ID))
//                item.toDoId=query.getLong(query.getColumnIndexOrThrow(COL_TODO_ID))
//                item.isCompleted=query.getInt(query.getColumnIndexOrThrow(COL_ITEM_IS_COMPLETED))==1
//                item.nameItem=query.getString(query.getColumnIndexOrThrow(COL_ITEM_NAME))
//
//
//                //todo.createdAt=query.getString(query.getColumnIndex(COL_CREATED_AT))
//                result.add(item)
//            }
//            while (query.moveToNext())
//
//
//        }
//        query.close()
//        return  result
//    }


//    fun updateToDoItem(item:ToDoItem){
//        var db = writableDatabase
//        var contentValues = ContentValues()
//        contentValues.put(COL_ITEM_NAME,item.nameItem)
//        contentValues.put(COL_TODO_ID, item.toDoId)
//        contentValues.put(COL_ITEM_IS_COMPLETED, item.isCompleted)
//
//        db.update(TABLE_TODO_ITEM, contentValues, "$COL_ITEM_ID=?", arrayOf(item.idItem.toString()))
//    }


    fun deleteToDO(toDoId: Long):Boolean{
        val db=writableDatabase
        val success =db.delete("$TABLE_TODO", "$COL_ID=?", arrayOf(toDoId.toString()))
        if (success!=-1) return true
        else return false
    }

//    fun markAsCompleted(toDoId: Long,isCompleted: Boolean) :Boolean{
//        val db=writableDatabase
//        val result:MutableList<ToDoItem> = ArrayList()
//        val query = db.rawQuery(" SELECT * FROM $TABLE_TODO_ITEM WHERE $COL_TODO_ID=$toDoId ", null)
//        if ( query.moveToFirst()){
//            do {
//                val item= ToDoItem()
//                item.idItem=query.getLong(query.getColumnIndexOrThrow(COL_ITEM_ID))
//                item.toDoId=query.getLong(query.getColumnIndexOrThrow(COL_TODO_ID))
//                item.isCompleted= isCompleted
//
//                    item.nameItem=query.getString(query.getColumnIndexOrThrow(COL_ITEM_NAME))
//
//
//
//               updateToDoItem(item)
//            }
//            while (query.moveToNext())
//
//
//        }
//        query.close()
//        return true
//
//    }
    fun updateToDo(toDo : ToDo) {
        var db = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(COL_NAME,toDo.name)

     db.update(TABLE_TODO,contentValues,"$COL_ID=?", arrayOf(toDo.id.toString()))
    }


}


