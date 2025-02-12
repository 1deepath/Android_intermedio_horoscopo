package com.onedeepath.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.onedeepath.horoscapp.databinding.FragmentHoroscopeBinding
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Aquarius
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Aries
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Cancer
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Capricorn
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Gemini
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Leo
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Libra
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Pisces
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Sagittarius
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Scorpio
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Taurus
import com.onedeepath.horoscapp.domain.model.HoroscopeInfo.Virgo
import com.onedeepath.horoscapp.domain.model.HoroscopeModel
import com.onedeepath.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    // Haciendo esto conectamos el fragment con el viewModel
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    // por seguridad creamos el verdadero binding con _, para luego llamar al binding con get() llamara al verdadero binding, para que nadie pueda acceder y romper esto.
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {

            // Creamos un type para que cuando elijamos un signo nos lleve al signo adecuado, para ello antes creamos una enum class de Horoscope Model
            // Donde en el maingraph, donde en horoscopeDetailFragment le dimos de argumento que recibiria una enum class que es el horoscopeModel, donde en este caso es la val type
            val type = when(it){
                Aquarius -> HoroscopeModel.Aquarius
                Aries -> HoroscopeModel.Aries
                Cancer -> HoroscopeModel.Cancer
                Capricorn -> HoroscopeModel.Capricorn
                Gemini -> HoroscopeModel.Gemini
                Leo -> HoroscopeModel.Leo
                Libra -> HoroscopeModel.Libra
                Pisces -> HoroscopeModel.Pisces
                Sagittarius -> HoroscopeModel.Sagittarius
                Scorpio -> HoroscopeModel.Scorpio
                Taurus -> HoroscopeModel.Taurus
                Virgo -> HoroscopeModel.Virgo
            }


            // esta fun llama a los fragmentos para poder navegar en ellos
            findNavController().navigate(
                // Con la terminacion en directions safeargs nos permite entrar en los actions de los fragment
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)

            )

        })

        // Con apply nos ahorramos llamar el binding y la vista, dentro del apply tiene los atributos
        binding.recyclerViewHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }

    }

    private fun initUIState() {

        // Siempre usar a nivel de fragment o activity lifecycleScope, se encarga de su propio ciclo de vida sin nuestra ayuda
        lifecycleScope.launch {
            // Con esto decimos cuando el ciclo de vida empiece collectealo.
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeViewModel.horoscope.collect{horoscopeList ->

                    //Cambios en el adapter

                    horoscopeAdapter.updateList(horoscopeList)


                }

            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}