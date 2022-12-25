package edu.fpt.apptruyentranh;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
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

import edu.fpt.apptruyentranh.retrofit.ApiAppDocTruyen;
import edu.fpt.apptruyentranh.retrofit.RetrofitClient;
import edu.fpt.apptruyentranh.utils.Utils;
import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginFragment extends Fragment {

    private TextView tvSingUp,tvquenmk;
    private Button btnLogin;
    boolean isLogin=false;

    
    TextInputLayout textInputUser;
    TextInputLayout textInputPassWord;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiAppDocTruyen apiAppDocTruyen;
    NavController mController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paper.init(getContext());
        apiAppDocTruyen= RetrofitClient.getInstane(Utils.BASE_URL_hai).create(ApiAppDocTruyen.class);

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
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
        tvquenmk=view.findViewById(R.id.tvquenpass);
        tvquenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mController.navigate(R.id.quenpassFragment);
            }
        });
        btnLogin = view.findViewById(R.id.btn_log_in);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUser() || !validatePassWord()){
                    return;
                }else {
                    String userName = textInputUser.getEditText().getText().toString().trim();
                    String passWordInPut = textInputPassWord.getEditText().getText().toString().trim();
                    checkLogin(userName,passWordInPut);
                }


            }
        });
        if(Paper.book().read("islogin")!=null){
            boolean flag=Paper.book().read("islogin");
            if(flag){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkLogin(Paper.book().read("email"),Paper.book().read("pass"));
                    }
                },1000);
            }
        }
        textInputUser = view.findViewById(R.id.text_in_put_user);
        textInputPassWord = view.findViewById(R.id.text_in_put_pass_word);
    }

    private void checkLogin(String userName,String passWordInPut  ) {



        compositeDisposable.add(apiAppDocTruyen.getUser(userName,passWordInPut).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){

                                Utils.user_current=userModel.getResult().get(0);
                                String idUser= String.valueOf(Utils.user_current.getId());
                                if(Utils.user_current.getUsername().equals(userName)&&Utils.user_current.getPassword().equals(passWordInPut)){
                                    Toast.makeText(getContext(),userModel.getMessage(),Toast.LENGTH_SHORT).show();
                                    SharedPreferences preferences= getContext().getSharedPreferences("MYPREFS", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=preferences.edit();
                                    editor.putString("idUser",idUser);
                                    editor.commit();
                                    isLogin=true;
                                    Utils.user_current = userModel.getResult().get(0);
                                    Paper.book().write("email", userName);
                                    Paper.book().write("pass", passWordInPut);
                                    Paper.book().write("islogin", isLogin);
                                    mController.navigate(R.id.listTruyenTranh);
                                }
                                else {
                                    Toast.makeText(getContext(),"email hoặc mật khẩu không đúng",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(getContext(),userModel.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        },throwable -> {
                            Toast.makeText(getContext(),"looxi server",Toast.LENGTH_SHORT).show();
                        }
                ));
        }
}


