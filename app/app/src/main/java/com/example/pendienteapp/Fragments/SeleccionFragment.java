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
import android.widget.TextView;

import com.example.pendienteapp.R;
import com.example.pendienteapp.Retrofit.INodeJS;
import com.example.pendienteapp.Retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SeleccionFragment extends Fragment {

    TextView id;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    INodeJS myAPI;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seleccion, container, false);

        Window window = ((Activity) getContext()).getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        Retrofit retrofit = RetrofitClient.getInstance();



        Bundle bundle = this.getArguments();

        /*id = view.findViewById(R.id.txtID);

        id.setText(bundle.getString("id"));*/
        myAPI = retrofit.create(INodeJS.class);

        datosEmpresa(1);
        return view;
    }

    private void datosEmpresa(int i) {
        compositeDisposable.add(myAPI.dataempresa(i)
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
                            Log.e("data",""+jsonObj2.getString("imagen_producto"));
                            
                            //txtLab.setText(jsonObj2.getString("imagen_producto"));
                        }
                    }
                })
        );

        /*
        compositeDisposable.add(myAPI.dataempresa(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if(s.contains("data")){
                            JSONObject jsonObj = new JSONObject(s);
                            JSONArray array = jsonObj.getJSONArray("data");
                            Log.e("data",""+jsonObj.get("data"));
                            //JSONObject jsonObj2 = (JSONObject) array.get(0);

                            //txtLab.setText(jsonObj2.getString("TXTDESCAULA"));
                        }
                    }
                })
        );*/
    }
}
