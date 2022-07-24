package com.cristina.cristinagallery.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cristina.cristinagallery.R
import com.cristina.cristinagallery.databinding.FragmentAuthorizationBinding
import com.cristina.cristinagallery.utils.LoadState
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.MaskedTextChangedListener.Companion.installOn
import dagger.hilt.android.AndroidEntryPoint

/**
 * Экран авторизации
 */
@AndroidEntryPoint
class AuthorizationFragment : Fragment() {

    private lateinit var binding: FragmentAuthorizationBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLoginMask()
        observeLoginError()
        observePasswordError()
        observeLoadState()

        binding.loginBtn.setOnClickListener {
            viewModel.auth()
        }

        binding.passwordEdt.doOnTextChanged { password, _, _, _ ->
            viewModel.setPassword(password.toString())
        }
    }

    private fun initLoginMask() {
        installOn(
            binding.loginEdt,
            PHONE_MASK,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                    viewModel.setLogin(extractedValue)
                }
            }
        )
    }

    private fun observeLoginError() {
        viewModel.mutableLoginError.observe(viewLifecycleOwner) { loginError ->
            when (loginError) {
                LoginError.EMPTY -> {
                    binding.loginTil.error = getString(R.string.field_cannot_be_empty)
                }
                LoginError.NOT_VALID -> {
                    binding.loginTil.error = getString(R.string.login_not_valid_error)
                }
                LoginError.VALID -> {
                    binding.loginTil.error = null
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun observePasswordError() {
        viewModel.mutablePasswordError.observe(viewLifecycleOwner) { passwordError ->
            when (passwordError) {
                PasswordError.EMPTY -> {
                    binding.passwordTil.error = getString(R.string.field_cannot_be_empty)
                }
                PasswordError.NOT_VALID -> {
                    binding.passwordTil.error = getString(R.string.password_not_valid_error)
                }
                PasswordError.VALID -> {
                    binding.passwordTil.error = null
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun observeLoadState() {
        viewModel.loadState.observe(viewLifecycleOwner) { loadState ->
            when (loadState) {
                LoadState.LOADING -> {
                    blockContent(true)
                }
                LoadState.ERROR -> {
                    blockContent(false)
                    Snackbar.make(
                        binding.root,
                        resources.getString(R.string.login_or_password_entered_incorrectly),
                        Snackbar.LENGTH_LONG
                    ).setAnchorView(binding.loginBtn).show()
                }
                LoadState.SUCCESS -> {
                    blockContent(false)
                    findNavController().navigate(R.id.action_authorizationFragment_to_mainFragment)
                }
                else -> {
                    // do nothing
                }
            }
        }
    }

    private fun blockContent(block: Boolean) {
        with(binding) {
            loginBtn.isLoading = block
            blockContentContainer.isVisible = block
        }
    }

    companion object {
        const val PHONE_MASK = "+7 ([000]) [000] [00] [00]"
    }
}