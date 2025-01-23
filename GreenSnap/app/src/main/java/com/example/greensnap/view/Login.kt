package com.example.greensnap.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.databinding.ActivityLoginBinding
import com.example.greensnap.dbconexion.UsuariosHelper
import com.example.greensnap.model.Usuario

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Recupero la lista de usuarios
        val users:ArrayList<Usuario> = UsuariosHelper.recuperarUsuarioBD(this)

        //Compruebo si el usuario ha guardado sus datos
        val sharedPreferences = this.getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        //Si el usuario ha guardado sus datos, se muestra un mensaje de bienvenida y se redirige a la pantalla principal.
        if(sharedPreferences.getBoolean("save", false)){
            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Bienvenido a GreenSnap", Toast.LENGTH_SHORT).show()
        }

        //Boton login
        binding.btnLogin.setOnClickListener {
            //Recupero los datos del usuario
            val user = binding.textUser.text.toString()
            val pass = binding.textPass.text.toString()
            //Compruebo que los campos no esten vacios
            if(user.isEmpty() || pass.isEmpty()){
                binding.textUser.error = "Campo obligatorio"
                binding.textPass.error = "Campo obligatorio"
                return@setOnClickListener
            }else{
                //Compruebo que el usuario y la contraseña sean correctos
                for(u in users){
                    Log.e("Carlos", "user: ${u.nombre} pass: ${u.password}")
                    if(u.nombre == user && u.password == pass || u.correo == user && u.password == pass){
                        //Guardo los datos del usuario en las preferencias compartidas.
                        if(binding.saveBox.isChecked){
                            editor.putBoolean("sesion", true)
                            editor.apply()
                        }

                        //Redirijo a la pantalla principal
                        val intent = Intent(this, PantallaPrincipal::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        //Boton para mostrar la contraseña.
        binding.showPassBox.setOnClickListener(){

            //Compruebo si el checkbox esta marcado
            if(binding.showPassBox.isChecked){
                //Muestro la contraseña
                binding.textPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }else{
                //Oculto la contraseña
                binding.textPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

    }
}