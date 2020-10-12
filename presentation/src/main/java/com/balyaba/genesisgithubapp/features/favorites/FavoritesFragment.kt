package com.balyaba.genesisgithubapp.features.favorites

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.balyaba.genesisgithubapp.R
import com.balyaba.genesisgithubapp.common.ui.hide
import com.balyaba.genesisgithubapp.common.ui.show
import com.balyaba.genesisgithubapp.common.vm.injectViewModel
import com.balyaba.genesisgithubapp.features.favorites.adapter.FavoritesAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_repositories.*
import kotlinx.android.synthetic.main.layout_empty.*
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: FavoritesViewModel

    private val adapter: FavoritesAdapter by lazy { FavoritesAdapter() }

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
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.processEvent(FavoritesViewEvent.OnLoadFavoritesEvent)
    }

    private fun initUI() {
        recyclerView.adapter = adapter
        adapter.listener = viewModel.adapterListener
    }

    private fun setupObserver() {
        viewModel.viewState().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.Loading -> showLoadingState()
                Status.Success -> {
                    adapter.replaceItems(it.repositories)
                    showSuccessState()
                }
                Status.Empty -> showEmptyState()
                Status.Error -> showErrorState()
            }
        })
        viewModel.viewEffects().observe(viewLifecycleOwner, {
            when (it) {
                is FavoritesViewEffect.OnOpenLink -> startActivity(it.intent)
                is FavoritesViewEffect.RemoveRepositoryByPosition -> {
                    adapter.removeItemByPosition(it.position)
                    if (adapter.itemCount == 0)
                        showEmptyState()
                }
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
        stateDescriptionView.text = getString(R.string.favorites_empty)
    }

    private fun showErrorState() {
        loadingView.hide()
        recyclerView.hide()
        emptyView.hide()
        errorView.show()
    }
}