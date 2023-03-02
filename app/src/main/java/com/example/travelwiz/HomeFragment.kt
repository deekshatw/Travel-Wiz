package com.example.travelwiz

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


<<<<<<< HEAD
    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataArrayList: ArrayList<TravelData>

    lateinit var imageId : Array<Int>
    lateinit var titleId : Array<String>
    lateinit var travelData : Array<String>


=======
>>>>>>> 53b4940 (Made some changes)
    /**
     * A simple [Fragment] subclass.
     * Use the [HomeFragment.newInstance] factory method to
     * create an instance of this fragment.
     */
    class HomeFragment : Fragment() {
        // TODO: Rename and change types of parameters
        private var param1: String? = null
        private var param2: String? = null

<<<<<<< HEAD
        private lateinit var hotelsCard : CardView
        private lateinit var exploreCard : CardView
=======
        private lateinit var adapter: MyAdapter
        private lateinit var recyclerView: RecyclerView
        private lateinit var dataArrayList: ArrayList<TravelData>

        lateinit var imageId: Array<Int>
        lateinit var titleId: Array<String>
        lateinit var travelData: Array<String>

        private lateinit var hotelsCard: CardView
        private lateinit var exploreCard: CardView
>>>>>>> 53b4940 (Made some changes)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getString(ARG_PARAM1)
                param2 = it.getString(ARG_PARAM2)
            }

        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_home, container, false)
        }


<<<<<<< HEAD

=======
>>>>>>> 53b4940 (Made some changes)
        companion object {
            /**
             * Use this factory method to create a new instance of
             * this fragment using the provided parameters.
             *
             * @param param1 Parameter 1.
             * @param param2 Parameter 2.
             * @return A new instance of fragment HomeFragment.
             */
            // TODO: Rename and change types and number of parameters
            @JvmStatic
            fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            hotelsCard = view.findViewById(R.id.hotelsCard)
            exploreCard = view.findViewById(R.id.exploreCard)
            hotelsCard.setOnClickListener {
                replaceFragment(HotelsFragment())
            }
            exploreCard.setOnClickListener {
                replaceFragment(ExploreFragment())
            }

            dataInitialize()
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView = view.findViewById(R.id.recyclerView1)

            recyclerView.layoutManager = layoutManager
            recyclerView.hasFixedSize()
            adapter = MyAdapter(dataArrayList)
            recyclerView.adapter = adapter


<<<<<<< HEAD



        }

        private fun dataInitialize(){
=======
        }

        private fun dataInitialize() {
>>>>>>> 53b4940 (Made some changes)

            dataArrayList = arrayListOf<TravelData>()
            imageId = arrayOf(
                R.drawable.jaipurr,
                R.drawable.keralaimg,
                R.drawable.karnataka,
                R.drawable.darjeeling,
                R.drawable.rajasthan_bg,
                R.drawable.kashmir
            )

            titleId = arrayOf(
                getString(R.string.jaipur),
                getString(R.string.kerala),
                getString(R.string.karnataka),
                getString(R.string.darjeeling),
                getString(R.string.rajasthan),
                getString(R.string.kashmir)
            )
<<<<<<< HEAD
            for (i in imageId.indices){
=======
            for (i in imageId.indices) {
>>>>>>> 53b4940 (Made some changes)
                val data = TravelData(imageId[i], titleId[i])
                dataArrayList.add(data)
            }

        }

<<<<<<< HEAD
        private fun replaceFragment(fragment: Fragment){
            val fragmentManager : FragmentManager =(activity as FragmentActivity). supportFragmentManager
            val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
        }
=======
        private fun replaceFragment(fragment: Fragment) {
            val fragmentManager: FragmentManager =
                (activity as FragmentActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
        }
    }
}
>>>>>>> 53b4940 (Made some changes)
