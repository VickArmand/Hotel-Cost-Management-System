package com.example.hotelcostmanagementsystem

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Toast

import android.widget.ProgressBar
import android.widget.Button
import com.example.hotelcostmanagementsystem.databinding.ActivityMainBinding


import android.R.attr.password
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase





class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnsubmit.setOnClickListener() {
                if (binding.txtuname.text.toString().isEmpty()||binding.txtpwd.text.toString().isEmpty()) {

                    Toast.makeText(this,"Please fill the above text fields", Toast.LENGTH_SHORT).show()


                }
                else if (binding.txtuname.text.toString().isEmpty()&& binding.txtpwd.text.toString().isEmpty()) {

                    Toast.makeText(this,"Please fill the above text fields",Toast.LENGTH_SHORT).show()



                }
                else {

                    var uname = binding.txtuname.text.toString()
                    var pwd = binding.txtpwd.text.toString()

    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.txtuname.text.toString(),binding.txtpwd.text.toString())
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {


                val intent =Intent(this,AdminHome::class.java)
                   startActivity(intent)
            } else {
                        Toast.makeText(this@MainActivity, "Login has failed", Toast.LENGTH_SHORT).show()
            }
        }
}
            }
    }


}
//

                    //code goes here








