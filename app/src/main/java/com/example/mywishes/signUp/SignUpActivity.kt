package com.example.mywishes.signUp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mywishes.R
import com.example.mywishes.base.BaseActivity
import com.example.mywishes.databinding.SignUpBinding
import com.example.mywishes.utilities.getViewModel
import com.example.mywishes.utilities.gotoLogin
import com.example.mywishes.utilities.showToast
import java.io.File


class SignUpActivity : BaseActivity<SignUpBinding, SignUpViewModel>() {

    val pickImage = 100
    var imageUri: Uri? = null
    lateinit var image: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpBinding.inflate(layoutInflater)
        val View = binding.root
        setContentView(View)



        binding.btnSignUp.setOnClickListener {
            val name = binding.edtName.text.toString()
            val number = binding.edtNumber.text.toString()
            val emailSign = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPass = binding.edtConfirmpassword.text.toString()

            viewModel?.checkValidations(name, emailSign, number, password, confirmPass)

        }


        image = binding.imgView
        val upload = binding.pickImage

        image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        upload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
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

    override fun initViewModel() = getViewModel<SignUpViewModel>()

    override fun initVariables() {

    }

    override fun setObservers() {
        viewModel?.onErrorLiveData?.observe(this, Observer {
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

        viewModel?.validationComplete?.observe(this, Observer {
            if (it) {
                val name = binding.edtName.text.toString()
                val number = binding.edtNumber.text.toString()
                val emailSign = binding.edtEmail.text.toString()
                val password = binding.edtPassword.text.toString()
                val confirmPass = binding.edtConfirmpassword.text.toString()


                val request = SignUpRequest(
                    name = name,
                    email = emailSign,
                    password = password,
                    mobile = number,
                    confirmPassword = confirmPass
                )
                val file = File(imageUri!!.path)
                showProgressBar()
                viewModel?.doSignUp(request,file)
            }
        })

        viewModel?.signUpResponse?.observe(this, Observer {
            hideProgressBar()
            showToast(getString(R.string.signup_success_message))
            gotoLogin(this)
        })
    }

    override fun inflateLayout(layoutInflater: LayoutInflater)= SignUpBinding.inflate(layoutInflater)
}