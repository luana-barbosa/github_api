package com.luanabarbosa.verity.githubapi.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.verity.githubapi.R
import com.luanabarbosa.verity.githubapi.databinding.GithubProfileReposFragmentBinding
import com.luanabarbosa.verity.githubapi.profile.data.model.GithubProfileRepoListModel
import com.luanabarbosa.verity.githubapi.profile.ui.adapter.GithubReposProfileAdapter
import com.luanabarbosa.verity.githubapi.ui.main.GithubMainActivity
import com.luanabarbosa.verity.toolkit.extensions.gone
import com.luanabarbosa.verity.toolkit.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class GithubProfileRepoFragment : Fragment() {
    private val profileViewModel: GithubUserProfileViewModel by viewModel()
    private val binding: GithubProfileReposFragmentBinding by lazy {
        GithubProfileReposFragmentBinding.inflate(
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
        changeStatusBarColor()
        profileViewModel.getProfileRepo()
        backPressed()
    }

    private fun setupObserver() {
        setupOnLoading()
        profileViewModel.observerRepos(this) {
            setupReposAdapter(it)
        }

        profileViewModel.isError.observe(viewLifecycleOwner) {
            setupOnError()
        }
    }

    private fun setupOnLoading() {
        with(binding) {
            containerSuccess.gone()
            loading.visible()
        }
    }

    private fun setupOnError() {
        with(binding) {
            containerSuccess.gone()
            errorContainer.visible()
            errorContainer.setOnClickListener {
                profileViewModel.getProfileRepo()
            }
        }
    }

    private fun setupReposAdapter(list: List<GithubProfileRepoListModel>) {
        with(binding) {
            containerSuccess.visible()
            repoList.visible()
            loading.gone()
            repoList.adapter = GithubReposProfileAdapter(list)
        }
    }

    private fun changeStatusBarColor() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.black
        )
    }

    private fun backPressed() {
        binding.toolbar.setOnClickListener {
            findNavController().navigate(R.id.action_reposFragment_to_profileFragment)
        }
    }

}
