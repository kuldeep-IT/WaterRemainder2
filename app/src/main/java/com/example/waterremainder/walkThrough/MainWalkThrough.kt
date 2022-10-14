package com.example.waterremainder.walkThrough

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.example.waterremainder.R
import com.example.waterremainder.adapter.PageOnBoardingAdapter
import com.example.waterremainder.databinding.ActivityMainWalkThroughBinding
import com.example.waterremainder.model.OnBoardingData

class MainWalkThrough : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainWalkThroughBinding
    private var list = ArrayList<OnBoardingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  DataBindingUtil.setContentView(this,R.layout.activity_main_walk_through)
        setUpViewPager()
        getListData()

        binding.btnNext.setOnClickListener(this)
        binding.btnPrev.setOnClickListener(this)
        binding.tvSkip.setOnClickListener(this)
    }

    private fun setUpViewPager() {
        binding.viewPager2.apply {
            adapter = PageOnBoardingAdapter(list)
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    binding.btnPrev.visibility = View.INVISIBLE.takeIf { position == 0 } ?: View.VISIBLE

                    if(position == list.size-1) {
                        binding.btnNext.text = "Start"
                    } else {
                        binding.btnNext.text = "Next"
                    }

                }
            })
        }
        binding.dotsIndicator.setViewPager2(binding.viewPager2)
        binding.viewPager2.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit =2
            getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        }

    }

    private fun getListData() {
        list.add(
            OnBoardingData(
                "Boosts skin health and beauty",
                "With dehydration, the skin can become more vulnerable to skin disorders and premature wrinkling.",
                R.raw.drink_water
            )
        )

        list.add(
            OnBoardingData(
                "Improve immune function",
                "When a person consumes it regularly, lime water can help strengthen the body’s defenses and may shorten the lifespan of colds and cases of flu, although there is limited scientific evidence to support this.",
                R.raw.water1
            )
        )

        list.add(
            OnBoardingData(
                "Helps Energize Muscles",
                "When muscle cells don't have adequate fluids, they don't work as well and performance can suffer,",
                R.raw.muscle_water
            )
        )


        Log.d("LIST_DATA", list.size.toString())
    }



    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnNext ->{
                val currentPosition = binding.viewPager2.currentItem
                if(currentPosition == list.size - 1){
                    Toast.makeText(this,"Now Start Our App", Toast.LENGTH_SHORT).show()
                    return
                }
                binding.viewPager2.setCurrentItem(currentPosition+1,true)
                binding.viewPager2.adapter!!.notifyDataSetChanged()

            }

            R.id.btnPrev ->{
                val currentPosition = binding.viewPager2.currentItem
                if (currentPosition == 0){
                    return
                }
                binding.viewPager2.setCurrentItem(currentPosition-1,true)
                binding.viewPager2.adapter?.notifyDataSetChanged()
            }

            R.id.tvSkip ->{
                Toast.makeText(this,"Now Start Our App", Toast.LENGTH_SHORT).show()

            }

        }
    }
}