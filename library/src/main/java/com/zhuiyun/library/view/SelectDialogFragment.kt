package com.inkbird.inkbirdapp.device.ibsm2sbt.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager


class SelectDialogFragment() : DialogFragment() {
    private lateinit var mAdapter: SelectorAdapter
    private lateinit var binding: LayoutSelectorRecyclerBinding
    private var onItemClick: ((Int) -> Unit)? = null

    companion object {
        fun newInstance(data: ArrayList<String>): SelectDialogFragment {
            val arg = Bundle()
            arg.putStringArrayList("data", data)
            val fragment = SelectDialogFragment()
            fragment.arguments=arg
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = LayoutSelectorRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data: MutableList<String>? = null
        if (arguments != null) {
            data = arguments?.getStringArrayList("data")
        }
        data?.let {
            mAdapter = SelectorAdapter(R.layout.item_selector, data)
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            binding.recycler.adapter = mAdapter
            binding.recycler.addItemDecoration(CustomDecoration(requireContext(), DividerItemDecoration.VERTICAL,false))
            mAdapter.setOnItemClickListener { adapter, view, position ->
                dismiss()
                onItemClick?.invoke(position)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window!!.decorView.setPadding(0, 0, 0, 0)
        //        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        val params = window.attributes

        //设置显示在底部
        params.gravity = Gravity.BOTTOM
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        window.attributes = params
        val decorView = window.decorView
        decorView.background = ColorDrawable(Color.TRANSPARENT)
    }

    fun setOnItemClickListener(onItemClick: ((Int) -> Unit)) {
        this.onItemClick = onItemClick
    }
}