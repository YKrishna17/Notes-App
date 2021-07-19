package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IAdapter {
    lateinit var viewModel: ViewModel
    private lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)
        bind.recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter= Adapter(this,this)
        bind.recyclerView.adapter=adapter
        viewModel =ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }
        })

    }
    override fun onItemClicked(note: Note){
        viewModel.deleteNote(note)
    }

    fun addNote(view: View) {
        val note= bind.input.text.toString()
        if(note.isNotEmpty()) viewModel.insertNote(Note(note))
        bind.input.setText("")
    }
}