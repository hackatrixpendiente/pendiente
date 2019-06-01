package com.example.pendienteapp.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pendienteapp.R;
import com.example.pendienteapp.Retrofit.INodeJS;
import com.example.pendienteapp.Retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SeleccionFragment extends Fragment {

    TextView descripcion;
    ImageView producto, empresa;
    Map<String, Integer> map = new HashMap<>();
    Map<String, Integer> map2 = new HashMap<>();
    CardView btnComprar;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    INodeJS myAPI;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seleccion, container, false);

        Window window = ((Activity) getContext()).getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));


        map.put("bembos", R.drawable.bembos);
        map.put("pardos", R.drawable.pardos);
        map2.put("hamburguesa", R.drawable.big);
        map2.put("pollo", R.drawable.llopo);

        Retrofit retrofit = RetrofitClient.getInstance();



        Bundle bundle = this.getArguments();
        final String idfi = bundle.getString("id");
        descripcion = view.findViewById(R.id.txt_descp);
        producto = view.findViewById(R.id.img_producto);
        empresa = view.findViewById(R.id.img_empresa);
        btnComprar = view.findViewById(R.id.btn_Comprar);
        //id.setText(bundle.getString("id"));
        myAPI = retrofit.create(INodeJS.class);


        datosEmpresa(Integer.parseInt(bundle.getString("id")));

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarFramento(new DonarFragment(),idfi);
            }
        });
        return view;
    }

    private void datosEmpresa(int i) {
        compositeDisposable.add(myAPI.dataempresa(i)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws JSONException {
                        if(s.contains("data")){
                            JSONObject jsonObj = new JSONObject(s);
                            JSONArray array = jsonObj.getJSONArray("data");
                            Log.e("data",""+jsonObj.get("data"));
                            JSONObject jsonObj2 = (JSONObject) array.get(0);
                            Log.e("data",""+jsonObj2.getString("imagen_producto"));
                            //Seteando datos
                            descripcion.setText(jsonObj2.getString("nombre_producto"));
                            String im_empresa = jsonObj2.getString("logo_empresa");
                            String im_producto = jsonObj2.getString("imagen_producto");
                            empresa.setImageResource(map.get(im_empresa));
                            producto.setImageResource(map2.get(im_producto));
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

    private void cargarFramento(Fragment fragment, String id){
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager ();
        manager.beginTransaction().replace(R.id.containerFrag,fragment).addToBackStack(null).commit();
    }
}
