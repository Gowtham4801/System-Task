package com.mkrdeveloper.retrofitgetrecyclerviewexample.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkrdeveloper.retrofitgetrecyclerviewexample.R
import com.mkrdeveloper.retrofitgetrecyclerviewexample.adapters.RvAdapter
import com.mkrdeveloper.retrofitgetrecyclerviewexample.databinding.ActivityMainBinding
import com.mkrdeveloper.retrofitgetrecyclerviewexample.models.UsersItem
import com.mkrdeveloper.retrofitgetrecyclerviewexample.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var rvAdapter: RvAdapter
    private lateinit var usersList: List<UsersItem>
    private lateinit var searchView: SearchView
    private lateinit var tvToolbarAnim : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchView = findViewById(R.id.searchView)
        tvToolbarAnim = findViewById(R.id.toolbar)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
                Log.d("Search", "menu")
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })
        usersData()
        toolBarAnim()


    }

    fun toolBarAnim(){
        val toolBarAnim: Animation =
            AnimationUtils.loadAnimation(this, R.anim.zoom_in_anime)
        tvToolbarAnim.startAnimation(toolBarAnim)

    }


    fun usersData() {
        usersList = listOf()

        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getAllUsers()
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    usersList = response.body()!!
                    binding.rvMain.apply {
                        rvAdapter = RvAdapter(usersList)
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }
        }
    }


    private fun filter(text: String) {
        val filteredlist: ArrayList<UsersItem> = ArrayList()

        for (item in usersList) {
//            if (item.userId.toLowerCase().contains(text.toLowerCase()) == true) {
//                filteredlist.add(item)
//    }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            rvAdapter.filterList(filteredlist)
        }
    }
}

