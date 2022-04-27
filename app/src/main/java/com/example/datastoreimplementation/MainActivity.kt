package com.example.datastoreimplementation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.example.datastoreimplementation.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainActivityViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = getViewModel()
        setListeners()
        setObserver()
    }

    private fun setListeners(){
        binding.etName.doOnTextChanged { text, _, _, _ ->
            binding.btSave.isEnabled = !text.isNullOrEmpty()
        }
        binding.btSave.setOnClickListener {
            viewModel.saveNameInPreferenceAndProto(binding.etName.text.toString())
        }
        binding.btGet.setOnClickListener {
            viewModel.getNameFromPreference()
        }
        binding.btGetProto.setOnClickListener {
            viewModel.getNameFromProto()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setObserver(){
        viewModel.name.observe(this, { name ->
            binding.tvGet.text = name
        })
        viewModel.message.observe(this, {message ->
            binding.tvGetProto.text = "Mensaje ${message.name} y su id: ${message.id}"
        })
        viewModel.isLoading.observe(this, { isLoading ->
            binding.progressBar1.visibility = if(isLoading) View.VISIBLE else View.GONE
        })
    }
}