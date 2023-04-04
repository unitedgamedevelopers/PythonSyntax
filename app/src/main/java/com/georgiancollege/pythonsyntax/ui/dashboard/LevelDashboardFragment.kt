package com.georgiancollege.pythonsyntax.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.georgiancollege.pythonsyntax.LevelActivity
import com.georgiancollege.pythonsyntax.R
import com.georgiancollege.pythonsyntax.databinding.FragmentLeveldashboardBinding

class LevelDashboardFragment : Fragment() {

    private var _binding: FragmentLeveldashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(LevelDashboardViewModel::class.java)

        _binding = FragmentLeveldashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layout = binding.layoutLevelButtons
        for(name in dashboardViewModel.getNames()){
            val buttonView = LayoutInflater.from(requireContext()).inflate(R.layout.level_listing_button_design,layout,false)
            val button = buttonView.findViewById<Button>(R.id.levelListingButton)
            button.text = name
            button.setOnClickListener{
                val intent = Intent(requireContext(), LevelActivity::class.java)
                startActivity(intent)
            }

            layout.addView(button)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}