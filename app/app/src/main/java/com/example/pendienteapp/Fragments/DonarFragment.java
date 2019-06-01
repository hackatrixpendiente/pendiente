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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class DonarFragment extends Fragment {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    INodeJS myAPI;
    private TextView txtEmpresa;
    private TextView txtDesc;
    private TextView txtMonto;
    private TextView txtSede;
    private Button btnComprar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donar, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) getContext()).getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(getResources()
                    .getColor(R.color.colorPrimary));

        }
        Retrofit retrofit = RetrofitClient.getInstance();
        Bundle bundle = this.getArguments();
        btnComprar = view.findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Compra realizada", Toast.LENGTH_SHORT).show();
            }
        });

        myAPI = retrofit.create(INodeJS.class);
        compositeDisposable.add(myAPI.dataempresa(1)
        //compositeDisposable.add(myAPI.dataempresa(Integer.parseInt(bundle.getString("id")))
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
                            txtEmpresa = getView().findViewById(R.id.txtEmpresa);
                            txtEmpresa.setText("Empresa: "+jsonObj2.getString("nombre_empresa"));
                            txtDesc = getView().findViewById(R.id.txtDesc);
                            txtDesc.setText(jsonObj2.getString("descripcion_producto"));
                            //txtLab.setText(jsonObj2.getString(nombre_empresa));
                            txtMonto = getView().findViewById(R.id.txtMonto);
                            txtMonto.setText("Precios: S/."+jsonObj2.getString("precio_producto"));
                            txtSede = getView().findViewById(R.id.txtSede);
                            txtSede.setText(jsonObj2.getString("nombre_sede"));

                        }
                    }
                })
        );
        return view;
    }

    private void datosEmpresa(int i) {


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
