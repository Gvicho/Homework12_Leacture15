package com.example.homework12_leacture15

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework12_leacture15.databinding.FragmentProductHomeBinding

class ProductHomeFragment : Fragment(),ItemClickListener {

    private var binding :FragmentProductHomeBinding? = null
    private lateinit var myAdaper: ProductRecyclerAdapter
    var productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductHomeBinding.inflate(inflater,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myAdaper = ProductRecyclerAdapter(this)

        productList = mutableListOf<Product>(
            Product(1,ProductType.BED,"Bed","Good", mutableListOf(Color_.GREY), mutableListOf(R.drawable.bed1_grey),100,4.1f,0),
            Product(2,ProductType.VASE,"Vase","Good", mutableListOf(Color_.WHITE,Color_.RED,Color_.BLACK,Color_.BLUE), mutableListOf(R.drawable.vase1_white,R.drawable.vase1_red),20,2.4f,0),
            Product(3,ProductType.SOFA,"Sofa","Good", mutableListOf(Color_.ORANGE), mutableListOf<Int>(R.drawable.sofa1_orange),110,5f,0),
            Product(4,ProductType.CHAIR,"Chair","asdgassdfa", mutableListOf(Color_.WHITE), mutableListOf<Int>(R.drawable.chair1_white),120,1f,0),
            Product(5,ProductType.VASE,"Vase","Very fragile,be carefull luke, this is threat", mutableListOf(Color_.WHITE,Color_.BLACK), mutableListOf(R.drawable.vase1_black),20,4.9f,0),
            Product(6,ProductType.CLOSET,"Closet","Big,Very Big, Ai shkafio ro ityvian", mutableListOf(Color_.GREY), mutableListOf(R.drawable.closet1_grey),500,4.3f,0)
        )

        setUp(productList)
    }

    private fun setUp(productList:MutableList<Product>){
        binding!!.recyclerForProduct.apply{
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@ProductHomeFragment.context, 2)
            adapter = myAdaper
            myAdaper.submitList(productList)
        }
        binding!!.searchEdtTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString())
            }
        })

        binding!!.backBtn.setOnClickListener{
            activity?.finish()
        }
    }

    private fun filterList(query: String) {
        val filteredList = if (query.isEmpty()) {
            productList
        } else {
            productList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.name.contains(query, ignoreCase = true)
            }
        }
        myAdaper.submitList(filteredList)
    }

    override fun onDestroyView(){
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProductHomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClicked(product: Product) {
        val name = product.name
        val prise = product.prise
        val rating = product.rating
        val imageList = product.imageList.toIntArray()
        var colorArray = intArrayOf()
        val description = product.desciption
        product.colorList.forEach {
            colorArray += it.colorId
        }
        val sold = product.sold
        val destination = ProductHomeFragmentDirections.actionProductHomeFragmentToProductDescriptionFragment(imageList,colorArray,name,sold,rating,prise,description )
        findNavController().navigate(destination)
    }
}