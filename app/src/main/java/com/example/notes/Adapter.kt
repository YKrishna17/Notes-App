package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val context: Context, private val listner: IAdapter) : RecyclerView.Adapter<Adapter.VH>() {
    inner class VH(itemView:View) : RecyclerView.ViewHolder(itemView){
        val text=itemView.findViewById<TextView>(R.id.text)
        val delete=itemView.findViewById<ImageView>(R.id.delete)
    }
    private val allNotes= ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val vH= VH(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
        vH.delete.setOnClickListener{
            listner.onItemClicked(allNotes[vH.adapterPosition])
        }
        return vH
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val i= allNotes[position]
        holder.text.text= i.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(new : List<Note>){
        allNotes.clear()
        allNotes.addAll(new)
        notifyDataSetChanged()
    }
}

interface IAdapter {
    fun onItemClicked(note: Note){}
}
