package com.example.hotelcostmanagementsystem.adminfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelcostmanagementsystem.Cashieradapter
import com.example.hotelcostmanagementsystem.Listcashiers
import com.example.hotelcostmanagementsystem.R
import com.google.firebase.database.*

class ViewcashiersFragment : Fragment() {
    private lateinit var db : DatabaseReference
    private  lateinit var rv: RecyclerView
    private lateinit var  arr: ArrayList<Listcashiers>
    private lateinit var cashieradapter: Cashieradapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_viewcashiers, container, false)
        rv= v.findViewById(R.id.rvcashiers)
        rv.layoutManager= LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        arr= arrayListOf<Listcashiers>()

        cashieradapter= Cashieradapter(this,arr)
        rv.adapter=cashieradapter
        getCashiersdata()
        var update1=v.findViewById<Button>(R.id.btnedit1)
        var cashierid=v.findViewById<TextView>(R.id.cashierid)
return v
    }

    private fun getCashiersdata() {
        db= FirebaseDatabase.getInstance().getReference("cashiers")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(cashierSnapshot in snapshot.children){
                        val cashier=cashierSnapshot.getValue(Listcashiers::class.java)
                        arr.add(cashier!!)
                    }
                    rv.adapter=Cashieradapter(this@ViewcashiersFragment,arr)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewcashiersFragment, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}