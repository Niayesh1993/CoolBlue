package xyz.zohre.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ui.R
import dagger.hilt.android.AndroidEntryPoint
import xyz.zohre.presentation.BaseFragment


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}