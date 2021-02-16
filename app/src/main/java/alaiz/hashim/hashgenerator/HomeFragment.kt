package alaiz.hashim.hashgenerator

import alaiz.hashim.hashgenerator.databinding.FragmentHomeBinding
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {
   private var _binding:FragmentHomeBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding= FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }



}