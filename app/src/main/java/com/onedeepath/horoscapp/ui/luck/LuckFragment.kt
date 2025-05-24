package com.onedeepath.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import java.util.random.RandomGenerator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.onedeepath.horoscapp.R
import com.onedeepath.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    // por seguridad creamos el verdadero binding con _, para luego llamar al binding con get() llamara al verdadero binding, para que nadie pueda acceder y romper esto.
    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

    }

    private fun initUI() {

        initListeners()

    }

    private fun initListeners() {

        binding.ivRoulette.setOnClickListener { spinRoulette() }

    }

    private fun spinRoulette() {

        val random = java.util.Random()
        val degrees =  random.nextInt(1440) + 360

        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        animator.duration = 2000
        animator.interpolator = DecelerateInterpolator()
        animator.doOnEnd {slideCard()}
        animator.start()

    }

    private fun slideCard() {

        val slideupAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        slideupAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.reverse.isVisible = true
            }

            override fun onAnimationEnd(p0: Animation?) {
                growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        binding.reverse.startAnimation(slideupAnimation)

    }

    private fun growCard() {

        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow)

        growAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.reverse.isVisible = false
                showPremonition()

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })

        binding.reverse.startAnimation(growAnimation)

    }

    private fun showPremonition() {
        val dissapearAnimation = AlphaAnimation(1.0f, 0.0f)
        dissapearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        dissapearAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible = true
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }


        })

        binding.preview.startAnimation(dissapearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


}