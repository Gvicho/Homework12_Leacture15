package com.example.homework12_leacture15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework12_leacture15.databinding.FragmentProductDescriptionBinding
import com.example.homework12_leacture15.databinding.FragmentProductHomeBinding

class ProductDescriptionFragment : Fragment() {

    private var binding : FragmentProductDescriptionBinding? = null
    private val args : ProductDescriptionFragmentArgs by navArgs()
    private lateinit var myAdaper: ColorRecyclerAdapter
    private var quantity = 0
    private var prise = 0

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

        val colorList = args.colorsArr.toMutableList()

        myAdaper = ColorRecyclerAdapter(colorList)
        setUp()
    }

    private fun setUp(){
        prise = args.prize
        binding!!.tvName.text = args.name
        binding!!.img.setImageResource(args.images[0])
        binding!!.backBtn.setOnClickListener{
            findNavController().popBackStack()
        }
        binding!!.tvDescription.text = args.description
        binding!!.tvRating.text = args.rating.toString()
        binding!!.tvSold.text = args.sold.toString()
        binding!!.colorsRecycler.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            adapter = myAdaper
        }

        binding!!.tvQuantity.text = quantity.toString()

        binding!!.minusBtn.setOnClickListener{
            if(quantity>0)quantity--
            binding!!.tvQuantity.text = quantity.toString()
            binding!!.tvPrise.text = (quantity*prise).toString()
        }

        binding!!.plusBtn.setOnClickListener{
            quantity++
            binding!!.tvQuantity.text = quantity.toString()
            binding!!.tvPrise.text = (quantity*prise).toString()
        }

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