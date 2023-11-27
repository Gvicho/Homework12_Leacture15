package com.example.homework12_leacture15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework12_leacture15.databinding.FragmentProductHomeBinding

class ProductHomeFragment : Fragment(),ItemClickListener {

    private var binding :FragmentProductHomeBinding? = null
    private lateinit var myAdaper: ProductRecyclerAdapter

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

        val productList  = mutableListOf<Product>(
            Product(1,ProductType.BED,"Bed","Good", mutableListOf(Color_.GREY), mutableListOf(R.drawable.bed1_grey),100,5f,0),
            Product(2,ProductType.VASE,"Vase","Good", mutableListOf(Color_.WHITE,Color_.RED), mutableListOf(R.drawable.vase1_white,R.drawable.vase1_red),100,5f,0),
            Product(3,ProductType.SOFA,"Sofa","Good", mutableListOf(Color_.ORANGE), mutableListOf<Int>(R.drawable.sofa1_orange),100,5f,0)
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
        product.colorList.forEach {
            colorArray += it.colorId
        }
        val sold = product.sold
        val destination = ProductHomeFragmentDirections.actionProductHomeFragmentToProductDescriptionFragment(imageList,colorArray,name,sold,rating,prise )
        findNavController().navigate(destination)
    }
}