package com.barisgungorr.bootcamprecipeapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteCardBinding
import com.barisgungorr.bootcamprecipeapp.data.entity.Favorite
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(
    private var viewModel: FavoriteViewModel,
    private var favoriteList: List<Favorite>
)
    :  RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: FragmentFavoriteCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentFavoriteCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = favoriteList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = favoriteList[position]
        val t = holder.binding

       t.textViewMealName.text = meal.mealsName

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${meal.mealsImageName}"
        Glide.with(holder.itemView.context).load(url).into(holder.binding.imageView)

        t.mealsDelete.setOnClickListener {
            Snackbar.make(
                it, "${meal.mealsName} ${it.resources.getString(R.string.removeText)}", Snackbar.LENGTH_LONG

            ).setAction(R.string.yesText) {
                deleteF(meal.mealsId)

            }.show()
        }
    }
    private fun deleteF(mealsId:Int) {
        viewModel.deleteF(mealsId )
    }
}