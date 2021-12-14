package com.example.hotelcostmanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class AdminMainActivity : AppCompatActivity() {
    private lateinit var db : DatabaseReference
    private  lateinit var rv: RecyclerView
    private lateinit var  arr: ArrayList<Listadmins>
    private lateinit var adminadapter: Adminadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        rv= findViewById(R.id.rvadmins)
        rv.layoutManager= LinearLayoutManager(this)
        rv.setHasFixedSize(true)
        arr= arrayListOf<Listadmins>()
        adminadapter= Adminadapter(this,arr)
        rv.adapter=adminadapter
        getAdminsdata()

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
                    rv.adapter = Adminadapter(this@AdminMainActivity,arr)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AdminMainActivity, "Error", Toast.LENGTH_SHORT).show()

            }
        })
    }

}