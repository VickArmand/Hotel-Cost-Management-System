package com.example.hotelcostmanagementsystem

import  android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.cashierslist.*

class Cashiers : AppCompatActivity() {
    private lateinit var db : DatabaseReference
    private  lateinit var rv: RecyclerView
    private lateinit var  arr: ArrayList<Listcashiers>
    private lateinit var cashieradapter: Cashieradapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cashiers)

        rv= findViewById(R.id.rvcashiers)
        rv.layoutManager=LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        arr= arrayListOf<Listcashiers>()

        cashieradapter= Cashieradapter(this,arr)
        rv.adapter=cashieradapter
        getCashiersdata()
var update1=findViewById<Button>(R.id.btnedit1)
var cashierid=findViewById<TextView>(R.id.cashierid)


    }

    private fun getCashiersdata() {
        db=FirebaseDatabase.getInstance().getReference("cashiers")
        db.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(cashierSnapshot in snapshot.children){
                        val cashier=cashierSnapshot.getValue(Listcashiers::class.java)
                        arr.add(cashier!!)
                    }
                    rv.adapter=Cashieradapter(this@Cashiers,arr)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Cashiers, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}