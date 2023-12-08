package com.luanabarbosa.verity.githubapi.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.luanabarbosa.verity.githubapi.R
import com.luanabarbosa.verity.githubapi.databinding.GithubHomeFragmentBinding
import com.luanabarbosa.verity.githubapi.home.data.model.GithubUsersListModel
import com.luanabarbosa.verity.githubapi.home.ui.adapter.GithubHomeUserAdapter
import com.luanabarbosa.verity.githubapi.ui.main.GithubMainActivity
import com.luanabarbosa.verity.toolkit.extensions.gone
import com.luanabarbosa.verity.toolkit.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubHomeFragment : Fragment() {
    private val homeViewModel: GithubHomeViewModel by viewModel()
    private val binding: GithubHomeFragmentBinding by lazy {
        GithubHomeFragmentBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? GithubMainActivity)?.showBottomNavBar()
        setupObserver()
        getUsersList()
        changeStatusBarColor()
    }

    private fun getUsersList() {
        with(binding) {
            loading.visible()
            containerSuccess.visible()
            errorContainer.gone()
            homeViewModel.getHomeUsers()
        }
    }

    private fun setupObserver() {
        homeViewModel.observerCards(this) {
            setupAdapter(it)
            searchListDisplay(it)
        }

        homeViewModel.isError.observe(viewLifecycleOwner) {
            with(binding) {
                containerSuccess.gone()
                errorContainer.visible()
            }
        }
    }

    private fun setupAdapter(users: List<GithubUsersListModel>) {
        with(binding) {
            rvList.visible()
            searchBar.visible()
            loading.gone()
            rvList.adapter = GithubHomeUserAdapter(users, requireContext())

        }
    }

    private fun searchListDisplay(list: List<GithubUsersListModel>) {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                resultListSearch(query, list)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                resultListSearch(newText, list)
                return false
            }
        })
    }

    private fun resultListSearch(search: String, list: List<GithubUsersListModel>) {
        val lisResultSearch: MutableList<GithubUsersListModel> = arrayListOf()
        for (element in list) {
            if (element.username.startsWith(search, ignoreCase = true)) {
                lisResultSearch.add(element)
            }
        }
        setupAdapter(lisResultSearch)
    }

    private fun changeStatusBarColor() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.black
        )
    }

}