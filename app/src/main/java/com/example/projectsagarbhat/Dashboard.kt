package com.example.projectsagarbhat

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Dashboard activity responsible for displaying image data.
 */
class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge display for immersive experience
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Apply window insets to adjust padding to accommodate system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val toolbar_dashboard= findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar_dashboard)



        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewDisplayImageData)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Initialize list to hold image data
        val myApiData = ArrayList<Images>()

        // Add a placeholder image for testing
        //myApiData.add(Images(1,"Sagar", "https://via.placeholder.com/600/92c952"))

        // Initialize RecyclerView adapter with initial data
        val recyclerViewAdapter = ImageAdapter(myApiData)
        recyclerView.adapter = recyclerViewAdapter

        // Make API call to fetch image data
        val apiCall = ApiClientImage.retrofitBuilder.getData()
        apiCall.enqueue(object : Callback<List<Images>> {
            override fun onResponse(
                call: Call<List<Images>>,
                response: Response<List<Images>>
            ) {
                val images: List<Images>? = response.body()
                Log.i("images", "$images")
                if (images != null) {
                    // Update list with fetched image data
                    myApiData.clear()
                    myApiData.addAll(images)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Images>>, t: Throwable) {
                // Log error message if API call fails
                Log.i("api_error", "Error in API: ${t.toString()}")
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout -> finish()
            R.id.action_exit -> finishAffinity()
        }
        return true
    }
}

