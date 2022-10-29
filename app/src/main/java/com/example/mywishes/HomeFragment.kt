package com.example.mywishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mywishes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mAdapter: MyAdapter
    private lateinit var recyclerView: RecyclerView

    private val mList = ArrayList<Model>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.rvData

        mList.add(Model("Mohit","Sunday 22-08-1999"))
        mList.add(Model("viru","Sunday 3-10-1998"))
        mList.add(Model("jimmy","Sunday 5-2-1998"))
        mList.add(Model("jimmy","Sunday 5-2-1998"))

        mAdapter = MyAdapter(mList)
        recyclerView.adapter =mAdapter
    }







}
