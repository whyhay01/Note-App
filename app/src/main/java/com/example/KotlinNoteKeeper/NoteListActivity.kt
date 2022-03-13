package com.example.KotlinNoteKeeper

import android.content.Intent
import android.icu.lang.UCharacter
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.HorizontalScrollView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.KotlinNoteKeeper.databinding.ActivityNoteListBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class NoteListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val TAG: String = "NoteListActivity"


    var binding : ActivityNoteListBinding? = null

    val noteDisplayLayout by lazy {
        LinearLayoutManager(this) }

    val recyclerViewAdapter by lazy {
        NoteListAdapter(this, DataManager.notes) }

//    val courseDisplayLayout by lazy {
//        GridLayoutManager(this, 2)}

//    val courseAdapter by lazy { NoteListAdapter(this,DataManager.courses.values.toList()) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        //implementing viewBinding
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //implementing Fab
        binding?.fab?.setOnClickListener(View.OnClickListener {
            val fabIntent = Intent(this, NoteActivity::class.java)
            startActivity(fabIntent)
        })

        //This method display note
        displayNote()


        //method that sets navigation drawer
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        val drawerLayout  = findViewById<DrawerLayout>(R.id.drawer_layout)

        if (drawerLayout != null) {
            val toggle = ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            navigationView?.setNavigationItemSelectedListener(this)
        }else{
            Log.e(TAG,"DrawerLayoutis pointing to=> $drawerLayout")

        }

    }

    private fun displayNote() {

        binding?.listItems?.layoutManager = noteDisplayLayout
        binding?.listItems?.adapter = recyclerViewAdapter
    }


    override fun onResume() {
        super.onResume()
        binding?.listItems?.adapter?.notifyDataSetChanged()
    }

//    override fun onBackPressed() {
//        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout?.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.items, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.id_note_fragment -> {
                displayNote()
            }

        }

        return true
    }

   /*         R.id.id_course_fragment -> {
                handleSelection("Course Selection")
            }

            R.id.id_share_fragment -> {
                handleSelection("Note Share selection")
            }
            R.id.id_send_fragment -> {
                handleSelection("Note Send button")
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun handleSelection(message: String) {
        drawerLayout?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }
*/
}