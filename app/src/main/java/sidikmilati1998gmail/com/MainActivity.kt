package sidikmilati1998gmail.com


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    internal lateinit var mDatabaseHelper: DatabaseHelper
    private var btnAdd: Button? = null
    private var btnView: Button? = null
    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById<View>(R.id.editText) as EditText
        btnAdd = findViewById<View>(R.id.btnAdd) as Button
        btnView = findViewById<View>(R.id.btnView) as Button
        mDatabaseHelper = DatabaseHelper(this)
        btnAdd!!.setOnClickListener {
            val newEntry = editText!!.text.toString()
            if (editText!!.length() != 0) {
                addData(newEntry)
                editText!!.setText("")
            } else {
                Toast.makeText(applicationContext, "Harus diisi", Toast.LENGTH_LONG).show()
            }
        }
        btnView!!.setOnClickListener {
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addData(newEntry: String) {
        val insertData = mDatabaseHelper.addData(newEntry)
        if (insertData) {
            Toast.makeText(this, "Data sukses insert", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show()
        }
    }
}
