//package com.example.todo
//
//import android.content.Context
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.CheckBox
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.todo.ToDos.ToDo
//import com.example.todo.ToDos.ToDoItem
//
//class ItemAdapter( val context: Context,val dbHelper: DBHelper ,val list:MutableList<ToDoItem>) :
//    RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(context).inflate(
//            R.layout.rv_sub_item_dashboard,parent,false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder,position: Int) {
//      //  val item = list.get(position)
//        holder.itemName.text = list[position].nameItem
//        holder.itemName.isChecked = list[position].isCompleted
//        holder.itemName.setOnClickListener{
//
//
//            list[position].isCompleted = !list[position].isCompleted
//            dbHelper.updateToDoItem(list[position])
//
////            val intent = Intent(context, ItemDashdoard::class.java)
////            intent.putExtra(INTENT_TODO_ID, list[position].idItem)
////            intent.putExtra(INTENT_TODO_NAME, list[position].nameItem)
//
//          // context.startActivity(intent)
//        }
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
//
//        val itemName: CheckBox = v.findViewById(R.id.checkbox)
//
//
//    }
//
//}