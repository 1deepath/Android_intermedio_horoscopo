package com.onedeepath.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onedeepath.horoscapp.databinding.FragmentHoroscopeBinding

class HoroscopeFragment : Fragment() {

    // por seguridad creamos el verdadero binding con _, para luego llamar al binding con get() llamara al verdadero binding, para que nadie pueda acceder y romper esto.
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}