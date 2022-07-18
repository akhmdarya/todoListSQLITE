//package com.example.todo
//
//import android.content.DialogInterface
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.MenuItem
//import android.widget.Button
//import android.widget.EditText
//import android.widget.LinearLayout
//import androidx.appcompat.app.AlertDialog
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.todo.ToDos.ToDo
//import com.example.todo.ToDos.ToDoItem
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import kotlinx.android.synthetic.main.activity_dashboard.*
//import kotlinx.android.synthetic.main.activity_item_dashdoard.*
//
//class ItemDashdoard : AppCompatActivity() {
//    lateinit var dbHelper :DBHelper
//    var todoId :Long= -1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_item_dashdoard)
//
//
//        dbHelper=DBHelper(this)
//
//        setSupportActionBar(item_toolbar)
//       supportActionBar?.setDisplayHomeAsUpEnabled(true)
//      //  supportActionBar?.setHomeButtonEnabled(true)
//
//        supportActionBar?.title=intent.getStringExtra(INTENT_TODO_NAME)
//        todoId=intent.getLongExtra(INTENT_TODO_ID,-1)
//
//
//        rv_item_dashboard.layoutManager= LinearLayoutManager(this)
//
//
//        val add_subtask = findViewById<FloatingActionButton>(R.id.add_item_subtask_butt)
//
//        add_subtask.setOnClickListener{
//            val modal = AlertDialog.Builder(this)
//            modal.setTitle("Add ToDo")
//            val view = layoutInflater.inflate(R.layout.modal_layout,null)
//            val toDoName = view.findViewById<EditText>(R.id.modal_text)
//            modal.setView(view)
//            modal.setPositiveButton("Add"){ _: DialogInterface, _: Int ->
//                if(toDoName.text.isNotEmpty()){
//                    val item = ToDoItem()
//                    item.nameItem=toDoName.text.toString()
//                    item.toDoId=todoId
//                    item.isCompleted=false
//
//                    dbHelper.addToDoItem((item))
//                    // Log.d(1.toString(),"@@@@@@@@@@@@@@@@@@@@@@@@")
//
//                    refreshList()
//                }
//
//
//            }
//
//            modal.setNegativeButton("Cancel"){ _: DialogInterface, _: Int ->
//
//
//            }
//            modal.show()
//
//        }
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return if(item?.itemId== android.R.id.home){
//            finish()
//                true
//        } else
//
//        super.onOptionsItemSelected(item)
//    }
//    override fun onResume() {
//        refreshList()
//        super.onResume()
//    }
//
//
//    private fun refreshList(){
//        rv_item_dashboard.adapter=ItemAdapter(this,dbHelper,dbHelper.getToDoItems(todoId))
//
//
//    }
//
//
//
//
//}