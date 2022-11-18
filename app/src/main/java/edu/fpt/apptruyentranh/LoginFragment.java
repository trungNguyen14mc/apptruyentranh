package edu.fpt.apptruyentranh;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

    private TextView tvSingUp;
    private Button btnLogin;


    TextInputLayout textInputEmail;
    TextInputLayout textInputUser;
    TextInputLayout textInputPassWord;

    NavController mController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private boolean validateUser(){
        String userName = textInputUser.getEditText().getText().toString().trim();

        if (userName.isEmpty()){
            textInputUser.setError("Field can't be empty");
            return false;
        }else if (userName.length() >15){
            textInputUser.setError("username too long");
            return false;
        }else {
            textInputUser.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            textInputEmail.setError("Please enter a valid email anddress");
            return false;
        }else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassWord(){
        String passWordInPut = textInputPassWord.getEditText().getText().toString().trim();

        if (passWordInPut.isEmpty()){
            textInputPassWord.setError("Field can't be empty");
            return false;
        }else {
            textInputPassWord.setError(null);
            return true;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        tvSingUp = view.findViewById(R.id.tv_sing_up);
        tvSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mController.navigate(R.id.signUpFragment);
            }
        });

        btnLogin = view.findViewById(R.id.btn_log_in);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !validateEmail() ||!validateUser() || !validatePassWord()){
                    return;
                }
                String input = "Email: " + textInputPassWord.getEditText().getText().toString();
                input += "\n";
                input = "UserName: " + textInputUser.getEditText().getText().toString();
                input += "\n";
                input = "PassWord: " + textInputPassWord.getEditText().getText().toString();

                mController.navigate(R.id.listTruyenTranh);
            }
        });

        textInputEmail = view.findViewById(R.id.text_in_put_layout);
        textInputUser = view.findViewById(R.id.text_in_put_user);
        textInputPassWord = view.findViewById(R.id.text_in_put_pass_word);
    }

}