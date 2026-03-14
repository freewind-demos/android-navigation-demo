package com.example.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 获取传递的参数
        val message = arguments?.getString("message") ?: "无消息"
        view.findViewById<TextView>(R.id.textViewMessage).text = message

        // 返回首页
        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
