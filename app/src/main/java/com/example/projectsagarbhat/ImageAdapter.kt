package com.example.projectsagarbhat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ImageAdapter(var imageDataList : ArrayList<Images>) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var id = itemView.findViewById<TextView>(R.id.textViewImageDataID)
        var image = itemView.findViewById<ImageButton>(R.id.imageButtonImageData)

        fun myBindData(imageData : Images){
            id.text = imageData.id.toString()
            Picasso.with(itemView.context).load(imageData.url).into(image)
            var imageButton = itemView.findViewById<ImageButton>(R.id.imageButtonImageData)
            imageButton.setOnClickListener {
                var DetailsIntent = Intent(it.context, DetailsActivity::class.java)
                var myBundle = Bundle()
                myBundle.putString("keyId", imageData.id.toString())
                myBundle.putString("keyTitle", imageData.title)
                myBundle.putString("keyUrl", imageData.url)
                DetailsIntent.putExtras(myBundle)
                it.context.startActivity(DetailsIntent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var myView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_images, parent, false)
        return MyViewHolder(myView)
    }

    override fun getItemCount(): Int {
        return imageDataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.myBindData(imageDataList[position])
    }
    }