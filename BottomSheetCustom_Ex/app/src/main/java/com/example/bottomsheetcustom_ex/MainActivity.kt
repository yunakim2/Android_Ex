package com.example.bottomsheetcustom_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.ActionMenuView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.action_bottom_bar.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.tool_bar)
        val amvMenu = findViewById<ActionMenuView>(R.id.amvMenu)


        amvMenu.setOnMenuItemClickListener(object : ActionMenuView.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                val id = item?.itemId
                when(id)
                {
                    R.id.action_menu1 -> txt_main.setText("메뉴1")
                    R.id.action_menu2-> txt_main.setText("메뉴2")
                    R.id.action_menu3-> txt_main.setText("메뉴3")
                }
                return onOptionsItemSelected(item)
            }

        })

        setSupportActionBar(toolbar)
        supportActionBar?.hide()
        supportActionBar?.setTitle(null)
        cl_main.setOnClickListener{
            if(supportActionBar!!.isShowing)
            {
                supportActionBar!!.hide()
            }
            else
            {
                supportActionBar!!.show()
            }


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.bottom_menu, amvMenu.menu)
        return super.onCreateOptionsMenu(menu)
    }
}