package edu.fpt.apptruyentranh;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import edu.fpt.apptruyentranh.retrofit.ApiAppDocTruyen;
import edu.fpt.apptruyentranh.retrofit.RetrofitClient;
import edu.fpt.apptruyentranh.utils.Utils;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class quenpassFragment extends Fragment {
    AppCompatButton appCompatButton;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ApiAppDocTruyen apiAppBanHang;
    EditText edit_email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quenpass, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiAppBanHang= RetrofitClient.getInstane(Utils.BASE_URL_hai).create(ApiAppDocTruyen.class);
        appCompatButton=view.findViewById(R.id.quenMk);
        edit_email=view.findViewById(R.id.edResetpass);
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPass();
            }
        });
    }

    private void resetPass() {
        String emai=edit_email.getText().toString();
        if(TextUtils.isEmpty(emai)){
            Toast.makeText(getContext(),"Bạn chưa nhập email",Toast.LENGTH_SHORT).show();
        }
        else {
            compositeDisposable.add(apiAppBanHang.resetpass(emai).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            userModel -> {
                                if(userModel.isSuccess()){
                                    Toast.makeText(getContext(),userModel.getMessage(),Toast.LENGTH_SHORT).show();
                                    NavController mController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
                                    mController.navigate(R.id.loginFragment);
                                }
                                else {
                                    Toast.makeText(getContext(),userModel.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            },throwable -> {
                                Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                    ));
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}