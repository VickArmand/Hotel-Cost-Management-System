package com.example.hotelcostmanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.hotelcostmanagementsystem.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.database.FirebaseDatabase




class Registration : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        auth= FirebaseAuth.getInstance()
        setContentView(binding.root)

          binding.spinnergender.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
              override fun onItemSelected(
                  adapterView1: AdapterView<*>?,
                  view: View?,
                  position: Int,
                  id: Long
              ) {
                 Toast.makeText(this@Registration,"You selected item ${adapterView1?.getItemAtPosition(position).toString()}",Toast.LENGTH_SHORT).show()
              }

              override fun onNothingSelected(parent: AdapterView<*>?) {
                  TODO("Not yet implemented")
              }


          }
        binding.spinnerrole.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                adapterView2: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@Registration,"You selected item ${adapterView2?.getItemAtPosition(position).toString()}",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }
        binding.btnreg.setOnClickListener() {

            if (binding.txtuname.text.toString().isEmpty() || binding.txtpwd.text.toString()
                    .isEmpty() || binding.txtsname.text.toString()
                    .isEmpty() || binding.txtfname.text.toString()
                    .isEmpty() || binding.txtlname.text.toString()
                    .isEmpty() || binding.txtconfirmpwd.text.toString()
                    .isEmpty() || binding.txtcontact.text.toString()
                    .isEmpty() || binding.txtemail.text.toString()
                    .isEmpty() || binding.txtidno.toString().isEmpty()
            ) {

                Toast.makeText(this, "Please fill the above text fields", Toast.LENGTH_SHORT).show()


            } else if (binding.txtuname.text.toString().isEmpty() && binding.txtpwd.text.toString()
                    .isEmpty() && binding.txtsname.text.toString()
                    .isEmpty() && binding.txtfname.text.toString()
                    .isEmpty() && binding.txtlname.text.toString()
                    .isEmpty() && binding.txtconfirmpwd.text.toString()
                    .isEmpty() && binding.txtcontact.text.toString()
                    .isEmpty() && binding.txtemail.text.toString()
                    .isEmpty() && binding.txtidno.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill in the above text fields", Toast.LENGTH_SHORT)
                    .show()


            } else if (binding.txtpwd.text.toString() != binding.txtconfirmpwd.text.toString()) {


                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT)
                    .show()
            } else {

                var idno = binding.txtidno.text.toString()
                var fname = binding.txtfname.text.toString()
                var sname = binding.txtsname.text.toString()
                var lname = binding.txtlname.text.toString()
                var contact = binding.txtcontact.text.toString()
                var email = binding.txtemail.text.toString()
                var uname = binding.txtuname.text.toString()
                var pwd = binding.txtpwd.text.toString()
                var cpwd = binding.txtconfirmpwd.text.toString()
                var role=binding.spinnerrole.selectedItem.toString()
                var gender=binding.spinnergender.selectedItem.toString()

                register(email,pwd)


if(role=="Cashier") {
    val database = FirebaseDatabase.getInstance().getReference("cashiers")
    val cashierid = database.push().key
    val cashierid2 = cashierid.toString()
    val cashier = Cashier(cashierid2, idno, fname, sname, lname, contact, email, gender, pwd)
    database.child(idno).setValue(cashier).addOnSuccessListener {
        binding.txtfname.text.clear()
        binding.txtsname.text.clear()
        binding.txtlname.text.clear()
        binding.txtcontact.text.clear()
        binding.txtemail.text.clear()
        binding.txtuname.text.clear()
        binding.txtpwd.text.clear()
        binding.txtconfirmpwd.text.clear()
        Toast.makeText(this, "Cashier successfully registered", Toast.LENGTH_SHORT)
            .show()
    }.addOnFailureListener {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
    }
//    val database2 = FirebaseDatabase.getInstance().getReference("users")
//     val usersid = database.push().key
//    val usersid2=usersid.toString()
//    val users = User(idno, uname, role, email, pwd)
//
//    database2.child(idno).setValue(users).addOnSuccessListener {
//        binding.txtfname.text.clear()
//        binding.txtsname.text.clear()
//        binding.txtlname.text.clear()
//        binding.txtcontact.text.clear()
//        binding.txtemail.text.clear()
//        binding.txtuname.text.clear()
//        binding.txtpwd.text.clear()
//        binding.txtconfirmpwd.text.clear()
//        Toast.makeText(this, "User successfully registered", Toast.LENGTH_SHORT)
//            .show()
//    }.addOnFailureListener {
//        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//    }
}
if(role=="Administrator")
{
    val database = FirebaseDatabase.getInstance().getReference("admin")
    val adminid = database.push().key

    val adminid2 = adminid.toString()
    val admin = Admin(adminid2,idno, fname, sname, lname,contact,email, uname, gender, pwd)
    database.child(idno).setValue(admin).addOnSuccessListener {
        binding.txtfname.text.clear()
        binding.txtsname.text.clear()
        binding.txtlname.text.clear()
        binding.txtcontact.text.clear()
        binding.txtemail.text.clear()
        binding.txtuname.text.clear()
        binding.txtpwd.text.clear()
        binding.txtconfirmpwd.text.clear()
        Toast.makeText(this, "Admin successfully registered", Toast.LENGTH_SHORT)
            .show()
    }.addOnFailureListener {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
    }

}


            }
       }



    }

    private fun register(email: String, pwd: String) {
        auth.createUserWithEmailAndPassword(email, pwd)
            .addOnCompleteListener{
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("Main", "Successfully created user: ${it.result!!.user!!.uid}")
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)

            }
            .addOnFailureListener{
                Log.d("Main","Failed to create a user ${it.message}")
                Toast.makeText(this,"Authentication failed",Toast.LENGTH_LONG).show()
            }
    }




    }
