package com.example.deerhunter.kotlindagger2mvpexample.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.deerhunter.kotlindagger2mvpexample.R
import com.example.deerhunter.kotlindagger2mvpexample.mvp.models.StackOverflowQuestions

/**
 * Created by deerhunter on 6/22/16.
 */
class GsonListAdapter(val context: Context) : RecyclerView.Adapter<GsonListAdapter.GsonViewHolder>() {
    private var questions = StackOverflowQuestions()

    override fun getItemCount(): Int {
        return questions.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GsonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.json_item, parent, false)
        return GsonViewHolder(view, view.findViewById(R.id.questionTitle) as TextView, view.findViewById(R.id.questionBody) as TextView)
    }

    override fun onBindViewHolder(holder: GsonViewHolder, position: Int) {
        holder.body.text = questions.items[position].link
        holder.title.text = questions.items[position].title
    }

    class GsonViewHolder(val view: View, val title: TextView, val body: TextView) : RecyclerView.ViewHolder(view) {
    }

    fun setData(data: StackOverflowQuestions) {
        questions.items.clear()
        questions.items.addAll(data.items)
        notifyDataSetChanged()
    }
}