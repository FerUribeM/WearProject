package com.ferbajoo.udemywearproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helper = NotificationHelper(this)

        button.setOnClickListener {
            Toast.makeText(this, "Se envio sin accion", Toast.LENGTH_SHORT).show()

            helper.createNotification("Titulo en helper", "Mensaje")
        }

        button2.setOnClickListener {
            Toast.makeText(this, "Se envio con accion", Toast.LENGTH_SHORT).show()
            helper.createNotification("Titulo en helper", "Mensaje con accion", true)
        }
    }


}
