package com.example.projectsagarbhat

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recycleView = findViewById<RecyclerView>(R.id.recyclerViewDisplayImageData)
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var myApiData = ArrayList<Images>()

        myApiData.add(Images(1,"Sagar", "https://via.placeholder.com/600/92c952"))


        var recycleViewAdapter = ImageAdapter(myApiData)
        recycleView.adapter = recycleViewAdapter

        var apiCall = ApiClientImage.retrofitBuilder.getData()

        apiCall.enqueue(object : Callback<List<Images>>{
            override fun onResponse(
                call: Call<List<Images>>,
                response: Response<List<Images>>
            ) {
                var products: List<Images>? = response.body()
                Log.i("products", "$products")
                if (products != null){
                    myApiData.clear()
                    myApiData.addAll(products)
                    recycleViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Images>>, t: Throwable) {
                Log.i("api_error", "Error in api ${t.toString()}")
            }
        })

    }
}


