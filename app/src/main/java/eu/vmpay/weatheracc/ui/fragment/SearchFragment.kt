package eu.vmpay.weatheracc.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import eu.vmpay.weatheracc.R
import eu.vmpay.weatheracc.adapters.SearchListAdapter
import eu.vmpay.weatheracc.viewModels.SearchViewModel
import kotlinx.android.synthetic.main.search_fragment.view.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by viewModels<SearchViewModel> { factory }

    private val searchListAdapter by lazy {
        SearchListAdapter {
            viewModel.storeCity(it)
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
                .apply {
                    ivSearch.setOnClickListener { viewModel.searchCity(etCity.text.toString()) }
                    etCity.setOnEditorActionListener { _, actionId, _ ->
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            viewModel.searchCity(etCity.text.toString())
                            true
                        } else {
                            false
                        }
                    }
                    rvCity.adapter = searchListAdapter

                    with(viewModel) {
                        cityList.observe(viewLifecycleOwner, Observer {
                            if (it.isNotEmpty()) {
                                searchListAdapter.submitList(it)
                                handleVisibility(textView, rvCity, false)
                            } else {
                                textView.text = getString(R.string.empty_list)
                                handleVisibility(textView, rvCity, true)
                            }
                        })
                        errorMessage.observe(viewLifecycleOwner, Observer {
                            textView.text = it
                            handleVisibility(textView, rvCity, true)
                        })
                    }
                }
    }

    private fun handleVisibility(textView: View, recyclerView: RecyclerView, shouldShowError: Boolean) {
        textView.visibility = if (shouldShowError) View.VISIBLE else View.GONE
        recyclerView.visibility = if (shouldShowError) View.GONE else View.VISIBLE
    }
}
