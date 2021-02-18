package alaiz.hashim.hashgenerator

import alaiz.hashim.hashgenerator.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        val hashAlgorithm = resources.getStringArray(R.array.hash_algorithm)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithm)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.generateButton.setOnClickListener {
            lifecycleScope.launch {
                animation()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private suspend fun animation() {
        binding.titleTextView.animate().alpha(0f).duration = 400L
        binding.generateButton.animate().alpha(0f).duration = 400L
        binding.textInputLayout.animate()
                .alpha(0f)
                .translationXBy(1000f)
                .duration = 400L
        binding.plainText.animate()
                .alpha(0f)
                .translationXBy(-1000f)
                .duration = 400L
        delay(300)
        binding.successView.animate()
                .alpha(1f).duration = 600L
        binding.successView.animate()
                .rotationBy(720f)
                .duration = 600L
        binding.successView.animate()
                .scaleXBy(800f)
                .duration = 800L
        binding.successView.animate()
                .scaleYBy(800f)
                .duration = 800L

        delay(500)
        binding.successImageView.animate()
                .alpha(1f).duration = 1000L
    }
}