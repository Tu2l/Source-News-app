package com.tu2l.source

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tu2l.source.adapters.TabsAdapter
import com.tu2l.source.api.source.ANI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<ViewPager?>(R.id.view_pager)
        val tabs = findViewById<TabLayout?>(R.id.tab_layout)

        val fragments: MutableList<Fragment> = ArrayList<Fragment>()
        val names: MutableList<String> = ArrayList<String>()

        names.add("World")
        fragments.add(NewsListFragment.newInstance(ANI.WORLD_BASE))
        names.add("National")
        fragments.add(NewsListFragment.newInstance(ANI.NATIONAL_BASE))
        names.add("Politics")
        fragments.add(NewsListFragment.newInstance(ANI.NATIONAL_POLITICS))
        names.add("Features")
        fragments.add(NewsListFragment.newInstance(ANI.NATIONAL_FEATURES))

        pager?.adapter = TabsAdapter(supportFragmentManager, names, fragments)
        tabs?.setupWithViewPager(pager)


        val toolBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)
        supportActionBar?.title = resources.getString(R.string.app_name)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search){
           startActivity(Intent(this,SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}