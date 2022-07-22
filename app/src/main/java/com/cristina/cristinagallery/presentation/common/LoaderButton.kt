package com.cristina.cristinagallery.presentation.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.cristina.cristinagallery.R
import com.cristina.cristinagallery.databinding.LoaderButtonViewBinding

/**
 * Кнопка с состоянием загрузки
 */
class LoaderButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(
    context,
    attributeSet
) {
    private val binding =
        LoaderButtonViewBinding.inflate(LayoutInflater.from(context), this)

    var text: String = ""
        set(value) {
            field = value
            binding.textTv.text = value
        }

    var isLoading: Boolean = false
        set(value) {
            field = value
            with(binding) {
                progressBar.isVisible = value
                textTv.isVisible = !value
                root.isEnabled = !value
            }
        }

    init {
        context.withStyledAttributes(attributeSet, R.styleable.LoaderButton) {
            text = getString(R.styleable.LoaderButton_textBtn) ?: ""
            isLoading = getBoolean(R.styleable.LoaderButton_isLoading, false)
        }
    }
}