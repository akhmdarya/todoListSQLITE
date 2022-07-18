package com.example.todo

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.ToDos.ToDo

class DashboardAdapter( val activity:Dashboard,val dbHelper: DBHelper,val list:MutableList<ToDo>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     return ViewHolder(LayoutInflater.from(activity).inflate(
         R.layout.rv_sub_dashboard,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.toDoName.text=item.name

//        holder.toDoName.setOnClickListener{
//
//            val intent = Intent(activity, ItemDashdoard::class.java)
//            intent.putExtra(INTENT_TODO_ID, list[position].id)
//            intent.putExtra(INTENT_TODO_NAME, list[position].name)
//
//            activity.startActivity(intent)
//        }


        holder.edit.setOnClickListener{
            activity.updateToDo(list[position])
            activity.refreshListToDo()
                    }


        holder.delete.setOnClickListener{
            val modal = AlertDialog.Builder(activity)
            modal.setTitle("Are you sure?")
            modal.setMessage("Do you want to delete this item?")
            modal.setPositiveButton("Delete") { _: DialogInterface, _: Int ->
                dbHelper.deleteToDO(list[position].id)
                activity.refreshListToDo()
            }
            modal.setNegativeButton("Cancel"){ _: DialogInterface, _: Int ->


            }
            modal.show()



        }



    }

    override fun getItemCount(): Int {
       return list.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

        val toDoName: TextView = v.findViewById(R.id.todo_name)


        val edit:ImageView=v.findViewById(R.id.iv_update)
        val delete:ImageView=v.findViewById(R.id.iv_delete)


    }

}