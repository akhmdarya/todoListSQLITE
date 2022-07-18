package com.example.todo

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.EditText

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ToDos.ToDo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {

     lateinit var dbHelper :DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

       // var toolbar = findViewById<Toolbar>(R.id.toolbar)


        setSupportActionBar(dashboard_toolbar)
        title = "Dashboard"



        dbHelper=DBHelper(this)

        rv_dashboard.layoutManager=LinearLayoutManager(this)

        val butt = findViewById<FloatingActionButton>(R.id.add_task_butt)
        butt.setOnClickListener{
            val modal = AlertDialog.Builder(this)
            modal.setTitle("Add ToDo")
            val view = layoutInflater.inflate(R.layout.modal_layout,null)
            val toDoName = view.findViewById<EditText>(R.id.modal_text)
            modal.setView(view)
            modal.setPositiveButton("Add"){ _: DialogInterface, _: Int ->
                if(toDoName.text.isNotEmpty()){
                     val toDo = ToDo()
                    toDo.name=toDoName.text.toString()

                    dbHelper.addToDo(toDo)
                   // Log.d(1.toString(),"@@@@@@@@@@@@@@@@@@@@@@@@")

                    refreshListToDo()
                }


            }

            modal.setNegativeButton("Cancel"){ _: DialogInterface, _: Int ->


            }
            modal.show()
        }
    }

    override fun onResume() {
        refreshListToDo()
        super.onResume()
    }

     fun refreshListToDo(){
        rv_dashboard.adapter=DashboardAdapter(this,dbHelper,dbHelper.getToDos())


    }

    fun updateToDo(toDo: ToDo){
        val modal = AlertDialog.Builder(this)
        modal.setTitle("Update ToDo")
        val view = layoutInflater.inflate(R.layout.modal_layout,null)
        val toDoName = view.findViewById<EditText>(R.id.modal_text)
        toDoName.setText(toDo.name)
        modal.setView(view)
        modal.setPositiveButton("Update"){ _: DialogInterface, _: Int ->
            if(toDoName.text.isNotEmpty()){

                toDo.name=toDoName.text.toString()
                dbHelper.updateToDo(toDo)


                // Log.d(1.toString(),"@@@@@@@@@@@@@@@@@@@@@@@@")

                refreshListToDo()
            }


        }

        modal.setNegativeButton("Cancel"){ _: DialogInterface, _: Int ->


        }
        modal.show()

    }





}