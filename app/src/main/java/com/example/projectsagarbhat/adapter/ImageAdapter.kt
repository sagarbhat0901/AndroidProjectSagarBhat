package com.example.projectsagarbhat.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectsagarbhat.DetailsActivity
import com.example.projectsagarbhat.R
import com.squareup.picasso.Picasso

/**
 * Adapter class for binding image data to RecyclerView.
 * @param imageDataList List of Images data to be displayed.
 */
class ImageAdapter(private val imageDataList: ArrayList<Images>) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    /**
     * ViewHolder class to hold views for each item in RecyclerView.
     * @param itemView The view for each item in the RecyclerView.
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Views in the layout item_layout_images
        private val id = itemView.findViewById<TextView>(R.id.textViewImageDataID)
        private val image = itemView.findViewById<ImageButton>(R.id.imageButtonImageData)

        /**
         * Binds data to views.
         * @param imageData The Image data object to bind to views.
         */
        fun myBindData(imageData: Images) {
            // Set ID text
            id.text = imageData.id.toString()
            // Load image using Picasso library
            Picasso.with(itemView.context).load(imageData.url).into(image)

            // Set click listener to open details activity
            image.setOnClickListener {
                val detailsIntent = Intent(it.context, DetailsActivity::class.java)
                // Put image details into Bundle and add to Intent
                val myBundle = Bundle().apply {
                    putString("keyId", imageData.id.toString())
                    putString("keyTitle", imageData.title)
                    putString("keyUrl", imageData.url)
                }
                detailsIntent.putExtras(myBundle)
                it.context.startActivity(detailsIntent)
            }
        }
    }

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the item layout and create ViewHolder
        val myView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_images, parent, false)
        return MyViewHolder(myView)
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return imageDataList.size
    }

    // Called by RecyclerView to display the data at the specified position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data at the specified position
        holder.myBindData(imageDataList[position])
    }
}
