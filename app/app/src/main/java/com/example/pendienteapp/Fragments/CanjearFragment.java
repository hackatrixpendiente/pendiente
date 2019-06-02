package com.example.pendienteapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pendienteapp.R;
import com.example.pendienteapp.Retrofit.INodeJS;
import com.example.pendienteapp.Retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CanjearFragment extends Fragment {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    INodeJS myAPI;
    private TextView txtEmpresa;
    private TextView txtDesc;
    private TextView txtMonto;
    private TextView txtSede;
    private Button btnCanjear, btnfb;
    private ImageView img_producto;
    Map<String, Integer> map2 = new HashMap<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canjear, container, false);
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
        btnCanjear = view.findViewById(R.id.btnCanjear);

        img_producto = view.findViewById(R.id.logo);
        map2.put("hamburguesa", R.drawable.big);
        map2.put("pollo", R.drawable.llopo);
        btnCanjear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Compra realizada", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Felicitaciones");
                dialog.setMessage("Has colaborado!");

                LayoutInflater inflater = LayoutInflater.from(getContext());
                View login_layout = inflater.inflate(R.layout.layout_login, null);

                dialog.setView(login_layout);

                dialog.show();

                btnfb = login_layout.findViewById(R.id.buttonFB);
                btnfb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });


            }
        });

        myAPI = retrofit.create(INodeJS.class);
        //compositeDisposable.add(myAPI.dataempresa(1)
        compositeDisposable.add(myAPI.dataempresa(Integer.parseInt(bundle.getString("id")))
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
                            txtSede = getView().findViewById(R.id.txtSede);
                            txtSede.setText(jsonObj2.getString("nombre_sede"));
                            String im_producto = jsonObj2.getString("imagen_producto");
                            img_producto.setImageResource(map2.get(im_producto));

                        }
                    }
                })
        );
        return view;
    }
}
