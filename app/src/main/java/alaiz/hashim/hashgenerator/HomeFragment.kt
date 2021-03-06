package alaiz.hashim.hashgenerator

import alaiz.hashim.hashgenerator.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    val hashViewModel:HashViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        binding.generateButton.setOnClickListener {
            onGenerateButtonClicked()
        }

        return binding.root
    }
    private fun onGenerateButtonClicked(){
        if(binding.plainText.text.isEmpty())
        {
            showSnackBar("Field Empty")
        }else {
            lifecycleScope.launch {
                animation()

                navigateToSuccessFragment(getHashedData())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val hashAlgorithm = resources.getStringArray(R.array.hash_algorithm)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, hashAlgorithm)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }


    private suspend fun animation() {
        binding.generateButton.isClickable=false
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
        delay(1500)
    }

    private fun navigateToSuccessFragment(digestValue:String){
        val direction=HomeFragmentDirections.actionHomeFragmentToSuccessFragment(digestValue)
        findNavController().navigate(direction)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
  fun getHashedData():String{
     val hashAlgorithm= binding.autoCompleteTextView.text.toString()
     val plaintext= binding.plainText.text.toString()
      return hashViewModel.getHash(plaintext,hashAlgorithm)
  }
    private fun showSnackBar(message:String){
        val snackBar= Snackbar.make(
                binding.rootLayout,
                message,
                Snackbar.LENGTH_SHORT
        )
        snackBar.setAction("Okey"){}
        snackBar.show()
    }
}