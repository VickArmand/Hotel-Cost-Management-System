package com.example.hotelcostmanagementsystem

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase


class Cashieradapter ( val c: Context, private val listcashiers: ArrayList<Listcashiers>) : RecyclerView.Adapter<Cashieradapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Cashieradapter.MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.cashierslist,parent,false)
        return Cashieradapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Cashieradapter.MyViewHolder, position: Int) {
        val currentitem: Listcashiers = listcashiers[position]
        holder.fname.text=currentitem.fname
        holder.sname.text=currentitem.sname
        holder.lname.text= currentitem.lname
        holder.id.text=currentitem.idno
        holder.email.text=currentitem.email
        holder.contact.text= currentitem.contact
        holder.delete1.setOnClickListener{
            FirebaseDatabase.getInstance().getReference("cashiers").child(holder.id.toString()!!).removeValue()

        }
        holder.edit1.setOnClickListener {
            val infilter= LayoutInflater.from(c)
            val v=infilter.inflate(R.layout.updatecashier,null)
            val addDialog= AlertDialog.Builder(c)
            addDialog.setView(v)
            addDialog.setPositiveButton("OK"){
                dialog,_ ->
                var newidno= v.findViewById<EditText>(R.id.cashieridno2).text.toString()
                var newemail=v.findViewById<EditText>(R.id.cashieremail2).text.toString()
                var newcontact=v.findViewById<EditText>(R.id.cashiercontact2).text.toString()
                if(newidno.isNotEmpty()||newcontact.isNotEmpty()||newemail.isNotEmpty()){
                    FirebaseDatabase.getInstance().getReference("cashiers").child(holder.id.toString()!!).child("idno").setValue(newidno)
                    FirebaseDatabase.getInstance().getReference("cashiers").child(holder.id.toString()!!).child("email").setValue(newemail)
                    FirebaseDatabase.getInstance().getReference("cashiers").child(holder.id.toString()!!).child("contact").setValue(newcontact)

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
        return listcashiers.size
    }
    public class MyViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){
        val fname: TextView =itemView.findViewById(R.id.cashierfname)
        val sname: TextView =itemView.findViewById(R.id.cashiersname)
        val lname: TextView =itemView.findViewById(R.id.cashiersurname)
        val id: TextView =itemView.findViewById(R.id.cashierid)
        val email: TextView =itemView.findViewById(R.id.cashieremail)
        val contact: TextView =itemView.findViewById(R.id.cashiercontact)
        val delete1 : Button = itemView.findViewById(R.id.btndelete1)
        val edit1 : Button = itemView.findViewById(R.id.btnedit1)

    }


}