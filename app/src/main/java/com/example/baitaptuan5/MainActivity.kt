package com.example.baitaptuan5

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tab_Layout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intView()
        setStaticPageAdapter()
        tab_Layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                val count =   fm.backStackEntryCount
                if(count >=1){
                    supportFragmentManager.popBackStack()
                }
                ft.commit()
            }

        })

    }
    /*
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {

            menuInflater.inflate(R.menu.main,menu)
            val searchItem = menu!!.findItem(R.id.movie_search)
            if (searchItem!=null){
                val searchView = searchItem.actionView as SearchView
                searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                })
            }
            return super.onCreateOptionsMenu(menu)
        }
    */
    private fun setStaticPageAdapter() {
        val myViewPagerAdapter: ViewStaticPageAdapter = ViewStaticPageAdapter(supportFragmentManager)
        myViewPagerAdapter.addFragment(NowPlaying(),"Now Playing")
        myViewPagerAdapter.addFragment(TopRate(),"Top Rate")
        viewPager.adapter = myViewPagerAdapter
        tab_Layout.setupWithViewPager(viewPager,true)



    }

    private fun intView() {
        viewPager = findViewById(R.id.main_views)
        tab_Layout = findViewById(R.id.tablayout)
    }
    class ViewStaticPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
        val fragments: MutableList<Fragment> = ArrayList<Fragment>()
        val titles: MutableList<String> = ArrayList<String>()
        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles.get(position)
        }
        fun addFragment(fragment: Fragment, title:String){
            fragments.add(fragment)
            titles.add(title)
        }
    }


}
