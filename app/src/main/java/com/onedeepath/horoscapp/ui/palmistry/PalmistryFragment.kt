package com.onedeepath.horoscapp.ui.palmistry

import android.os.Bundle
import android.text.BoringLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.onedeepath.horoscapp.Manifest
import com.onedeepath.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object {

        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA

    }

    // por seguridad creamos el verdadero binding con _, para luego llamar al binding con get() llamara al verdadero binding, para que nadie pueda acceder y romper esto.
    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){isGranted ->

        if (isGranted){


        }else{

            Toast.makeText(requireContext(), "Acepta los permisos crack")

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (checkCameraPermission()) {



        }else {

            requestPermissionLauncher.launch(CAMERA_PERMISSION)

        }


    }

    private fun checkCameraPermission(): Boolean {

        return PermissionChecker
            .checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION) == PermissionChecker.PERMISSION_GRANTED

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


}