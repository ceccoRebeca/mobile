package com.example.impostoderenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.impostoderenda.databinding.TelaCalculoBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: TelaCalculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TelaCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcularButton.setOnClickListener(this)
    }

    override fun onClick(view: View){
        if(view.id == R.id.calcular_button){
            fazerCalculo()
        }
    }

    fun fazerCalculo(): Float{
        var salario = binding.editSalario.text.toString().toFloat()
        var gastos = binding.editGasto.text.toString().toFloat()

        return salario
    }

}