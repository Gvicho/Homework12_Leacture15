package com.example.homework12_leacture15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homework12_leacture15.databinding.FragmentProductDescriptionBinding
import com.example.homework12_leacture15.databinding.FragmentProductHomeBinding

class ProductDescriptionFragment : Fragment() {

    private var binding : FragmentProductDescriptionBinding? = null
    private val args : ProductDescriptionFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDescriptionBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.textView.text = args.name
        binding!!.img.setImageResource(args.images[0])
    }

    override fun onDestroyView(){
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProductDescriptionFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}