package edu.fpt.apptruyentranh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment {

    private Button btnSignUp;
    private Button btnLogOut;
    NavController mController;

    TextInputLayout textInputEmail;
    TextInputLayout textInputPassWord;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        btnSignUp = view.findViewById(R.id.btn_sign_up);
        
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() || !validatePassWord()){
                    return;
                }
                String input = "Email: " + textInputPassWord.getEditText().getText().toString();
                input += "\n";
                input = "PassWord: " + textInputPassWord.getEditText().getText().toString();
                Toast.makeText(getActivity(), "Đăng kí thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogOut = view.findViewById(R.id.btn_log_out);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.navigate(R.id.loginFragment);
            }
        });


        textInputEmail = view.findViewById(R.id.text_in_put_email);
        textInputPassWord = view.findViewById(R.id.text_in_put_pass_word);
    }
}