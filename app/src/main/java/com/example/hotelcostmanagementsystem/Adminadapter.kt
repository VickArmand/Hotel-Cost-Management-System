package com.example.hotelcostmanagementsystem

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class Adminadapter (val c:Context,private val listadmins: ArrayList<Listadmins>) : RecyclerView.Adapter<Adminadapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val itemView= LayoutInflater.from(parent.context).inflate(R.layout.adminslist,parent,false)
      return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Adminadapter.MyViewHolder, position: Int) {
        val currentitem=listadmins[position]

        holder.fname.text=currentitem.fname
        holder.sname.text=currentitem.sname
        holder.lname.text= currentitem.lname
        holder.id.text=currentitem.idno
        holder.email.text=currentitem.email
        holder.contact.text= currentitem.contact
        holder.delete2.setOnClickListener {
//            AlertDialog.Builder(c).setTitle("Delete").setIcon(R.drawable.ic_baseline_delete_24).setMessage("Are you sure you want to delete this information")
//                .setPositiveButton("YES"){
//                        dialog,_ ->
                    FirebaseDatabase.getInstance().getReference("admin").child(currentitem.idno!!).removeValue()
//                    notifyDataSetChanged()
                    Toast.makeText(c,"Delete Successful",Toast.LENGTH_LONG)
//                    dialog.dismiss()
                }
//            .setNegativeButton("NO"){
//                    dialog,_ ->
//                dialog.dismiss()
//            }

//        }
        holder.edit2.setOnClickListener {
            val infilter= LayoutInflater.from(c)
            val v=infilter.inflate(R.layout.updatecashier,null)
            val addDialog= AlertDialog.Builder(c)
            addDialog.setView(v)
            addDialog.setPositiveButton("OK"){
                    dialog,_ ->
                var newidno= v.findViewById<EditText>(R.id.adminidno2).text.toString()
                var newemail=v.findViewById<EditText>(R.id.adminemail2).text.toString()
                var newcontact=v.findViewById<EditText>(R.id.admincontact2).text.toString()
                if(newidno.isNotEmpty()&& newcontact.isNotEmpty()&& newemail.isNotEmpty()){
                    FirebaseDatabase.getInstance().getReference("admin").child(currentitem.idno!!).child("idno").setValue(newidno)
                    FirebaseDatabase.getInstance().getReference("admin").child(currentitem.idno!!).child("email").setValue(newemail)
                    FirebaseDatabase.getInstance().getReference("admin").child(currentitem.idno!!).child("contact").setValue(newcontact)

                }
            }
            addDialog.setNegativeButton("CANCEL"){
                    dialog,_ ->
                dialog.dismiss()
            }
            addDialog.create()
            addDialog.show()
        }
        }


    override fun getItemCount(): Int {
        return listadmins.size
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        val fname: TextView=itemView.findViewById(R.id.adminfname)
        val sname: TextView=itemView.findViewById(R.id.adminsname)
        val lname: TextView=itemView.findViewById(R.id.adminsurname)
        val id: TextView =itemView.findViewById(R.id.adminid)
        val email: TextView =itemView.findViewById(R.id.adminemail)
        val contact: TextView =itemView.findViewById(R.id.admincontact)
        val delete2 : Button = itemView.findViewById(R.id.btndelete2)
        val edit2 : Button = itemView.findViewById(R.id.btnedit2)


    }
}