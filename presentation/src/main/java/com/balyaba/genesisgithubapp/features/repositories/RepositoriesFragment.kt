package com.balyaba.genesisgithubapp.features.repositories

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.balyaba.genesisgithubapp.R
import com.balyaba.genesisgithubapp.common.vm.injectViewModel
import com.balyaba.genesisgithubapp.features.repositories.adapter.RepositoriesAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoriesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: RepositoriesViewModel

    private val adapter: RepositoriesAdapter by lazy { RepositoriesAdapter() }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(factory = viewModelFactory)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupObserver()
    }

    private fun initUI() {
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.repositories.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }
}