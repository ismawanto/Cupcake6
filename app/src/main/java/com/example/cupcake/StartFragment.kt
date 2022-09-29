/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentStartBinding
import com.example.cupcake.model.OrderViewModel

/**
 * Pada bagian ini akan menampilan layar pertama aplikasi Capcake. Sehingga pengguna dapat memilih berapa banyak capcake yang akan dipesan
 */
class StartFragment : Fragment() {

    // Instance objek yang mengikat sesuai dengan tata letak fragment_start.xml
    // Pada bagian ini terjadi pemanggilan kembali untuk pengaktifan antara onCreateView() dan onDestroyView()
    // Pada bagian ini terjadi tampilan yang dilampirkan ke fragmen.
    private var binding: FragmentStartBinding? = null

    // Penggunaan delegasi properti kotlin oleh activityViewModels() dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    /**
     * Mulai pesan dengan jumlah cupcake yang diinginkan dan usap layar berikutnya.
     */
    fun orderCupcake(quantity: Int) {
        // Perbarui model tampilan dengan jumlah keinginan.
        sharedViewModel.setQuantity(quantity)

        // Jika belum ada rasa yang dipilih maka rasa vanila sebagai rasa default.
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // Usap layar berikutnya untuk pemilihan rasa.
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    /**
     * Metode daur hidup fragmen ini dipanggil ketika urutan tingkatan tampilan terkait dengan fragmen
     * Penghapusan akan mengakibatkan pembersihan objek yang mengikat.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}