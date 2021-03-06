package alaiz.hashim.hashgenerator

import alaiz.hashim.hashgenerator.databinding.FragmentSuccessBinding
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SuccessFragment : Fragment() {
    val args:SuccessFragmentArgs by navArgs()
    private var _binding: FragmentSuccessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)
        binding.copyButton.setOnClickListener {
            onCopyClicked()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.hashDigest.text=args.digestValue
    }
       private fun onCopyClicked(){
            copyToClipboard(args.digestValue)
           lifecycleScope.launch {
               applyAnimation()
           }
        }

    @SuppressLint("ServiceCast")
    private fun copyToClipboard(hash:String){
        val clipboardManager=requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val copyData=ClipData.newPlainText("digest value",hash)
        clipboardManager.setPrimaryClip(copyData)
    }
    private suspend fun applyAnimation(){
        binding.include.messageView.animate().translationY(80.0F).duration=200L
        binding.include.textView.animate().translationY(80.0F).duration=200L
        delay(2000L)
        binding.include.messageView.animate().translationY(-80.0F).duration=500L
        binding.include.textView.animate().translationY(-80.0F).duration=500L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}