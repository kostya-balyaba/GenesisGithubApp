package com.balyaba.genesisgithubapp.features.repositories

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.balyaba.domain.entities.Repository
import com.balyaba.genesisgithubapp.R
import com.balyaba.genesisgithubapp.common.ui.hide
import com.balyaba.genesisgithubapp.common.ui.onQueryTextChange
import com.balyaba.genesisgithubapp.common.ui.show
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
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val searchMenuItem = menu.findItem(R.id.action_search)
        val searchView = searchMenuItem.actionView as SearchView
        searchView.onQueryTextChange {
            if (it.isNotEmpty() && it.length > 2) {
                viewModel.processEvent(RepositoriesViewEvent.OnSearchRequest(it))
            }
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val adapterListener = object : RepositoriesAdapter.OnItemClickListener {
        override fun onRepositoryClick(repository: Repository) {
            repository.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

    private fun initUI() {
        recyclerView.adapter = adapter
        adapter.listener = adapterListener
    }

    private fun setupObserver() {
        viewModel.repositories.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        viewModel.viewState().observe(viewLifecycleOwner, {
            when (it.status) {
                is Status.Loading -> showLoadingState()
                is Status.Success -> showSuccessState()
                is Status.Empty -> showEmptyState()
                is Status.Error -> showErrorState()
            }
        })
    }

    private fun showLoadingState() {
        errorView.hide()
        emptyView.hide()
        recyclerView.hide()
        loadingView.show()
    }

    private fun showSuccessState() {
        errorView.hide()
        emptyView.hide()
        loadingView.hide()
        recyclerView.show()
    }

    private fun showEmptyState() {
        errorView.hide()
        loadingView.hide()
        recyclerView.hide()
        emptyView.show()
    }

    private fun showErrorState() {
        loadingView.hide()
        recyclerView.hide()
        emptyView.hide()
        errorView.show()
    }
}