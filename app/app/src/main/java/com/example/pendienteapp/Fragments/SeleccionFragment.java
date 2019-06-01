package com.example.pendienteapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.pendienteapp.R;
import com.example.pendienteapp.Retrofit.INodeJS;
import com.example.pendienteapp.Retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SeleccionFragment extends Fragment {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    INodeJS myAPI;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seleccion, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) getContext()).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(getResources()
                    .getColor(R.color.colorPrimary));

        }
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(INodeJS.class);
        compositeDisposable.add(myAPI.datosEmpresa(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("data")){
                            JSONObject jsonObj = new JSONObject(s);
                            JSONArray array = jsonObj.getJSONArray("data");
                            Log.e("data",""+jsonObj.get("data"));
                            JSONObject jsonObj2 = (JSONObject) array.get(0);

                            //txtLab.setText(jsonObj2.getString("TXTDESCAULA"));
                        }
                    }
                })
        );
        return view;
    }
}
