package com.example.hotelcostmanagementsystem.adminfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelcostmanagementsystem.Adminadapter
import com.example.hotelcostmanagementsystem.Listadmins
import com.example.hotelcostmanagementsystem.R
import com.google.firebase.database.*


class ViewadminsFragment : Fragment() {

    private lateinit var db : DatabaseReference
    private  lateinit var rv: RecyclerView
    private lateinit var  arr: ArrayList<Listadmins>
    private lateinit var adminadapter: Adminadapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      val v=inflater.inflate(R.layout.fragment_viewadmins, container, false)
        rv= v.findViewById(R.id.rvadmins)
        rv.layoutManager= LinearLayoutManager(activity)
        rv.setHasFixedSize(true)
        arr= arrayListOf<Listadmins>()
        adminadapter= Adminadapter(this,arr)
        rv.adapter=adminadapter
        getAdminsdata()
        return v
    }
    private fun getAdminsdata() {
        db= FirebaseDatabase.getInstance().getReference("admin")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (adminSnapshot in snapshot.children) {
                        val admin = adminSnapshot.getValue(Listadmins::class.java)
                        arr.add(admin!!)
                    }
                    rv.adapter = Adminadapter(this@ViewadminsFragment,arr)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewadminsFragment, "Error", Toast.LENGTH_SHORT).show()

            }
        })
    }


}