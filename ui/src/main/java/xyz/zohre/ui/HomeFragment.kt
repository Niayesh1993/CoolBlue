package xyz.zohre.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.ui.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import xyz.zohre.presentation.BaseFragment
import xyz.zohre.presentation.shortToast


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val adapter = ProductRecyclerAdapter()
    private val viewModel: HomeViewModel by getLazyViewModel()


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product_recycler.adapter = adapter
        product_recycler.itemAnimator = DefaultItemAnimator()

        initObservers()
        viewModel.searchProducts()

    }

    private fun initObservers() {
        viewModel.products.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { it1 -> adapter.insertItems(it1) }
            }
        )
        viewModel.showError.observe(
            viewLifecycleOwner,
            Observer { event ->
                event.getContentIfNotHandled()?.let { textData ->
                    textData.shortToast(requireContext())
                }
            }
        )
    }
}