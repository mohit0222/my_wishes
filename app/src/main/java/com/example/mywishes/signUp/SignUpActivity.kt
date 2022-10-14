package com.example.mywishes.signUp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mywishes.databinding.SignUpBinding


class SignUpActivity : AppCompatActivity() {

    val pickImage = 100
    var imageUri: Uri? = null
   lateinit var image :ImageView

    private lateinit var binding: SignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        observeLiveDataSign()
        binding.btnSignUp.setOnClickListener {
            val name = binding.edtName.text.toString()
            val number = binding.edtNumber.text.toString()
            val emailSign = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPass = binding.edtConfirmpassword.text.toString()

            signUpViewModel.checkValidations(name, emailSign, number, password, confirmPass)

        }



        image = binding.imgView
        val upload = binding.pickImage

        image.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,pickImage)

//        val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        startActivityForResult(camera_intent, pickImage)
        }

        upload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery,pickImage)
        }
    }

    fun observeLiveDataSign() {

        signUpViewModel.onErrorLiveData.observe(this, Observer {
            if (it == Email_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show()
            } else if (it == password_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show()
            } else if (it == NAME_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show()
            } else if (it == phone_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter Number", Toast.LENGTH_SHORT).show()
            } else if (it == confirm_password_VALIDATION_FAILED) {
                Toast.makeText(this, "Please enter confirm Password", Toast.LENGTH_SHORT).show()

            }
        })

        signUpViewModel.validationComplete.observe(this, Observer {
            if (it) {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        })
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("IMAGE_RESULT", "onActivityResult ${resultCode} ${requestCode}")
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            Toast.makeText(this@SignUpActivity, "" + data, Toast.LENGTH_LONG).show()
            imageUri = data?.data
            image.setImageURI(imageUri)
        }

    }

}