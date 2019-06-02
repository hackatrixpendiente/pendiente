package com.example.pendienteapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import io.reactivex.disposables.Disposable;
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
    private Button btnComprar, btnfb;
    private EditText cantidadText,mensajeText;
    private ImageView img_producto;
    public static String FACEBOOK_URL = "https://www.facebook.com/homer.lopezvidal";
    public static String FACEBOOK_PAGE_ID = "homer.lopezvidal";
    Map<String, Integer> map2 = new HashMap<>();
    private Integer idusuario = 1;
    private Integer idproducto;


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

        img_producto = view.findViewById(R.id.logo);
        map2.put("hamburguesa", R.drawable.big);
        map2.put("pollo", R.drawable.llopo);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidadText = v.findViewById(R.id.cantidadText);
                mensajeText = v.findViewById(R.id.mensajeText);
                realizarCompra(idusuario,Integer.parseInt(cantidadText.getText().toString()),mensajeText.getText().toString());
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
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(getContext());
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                        Toast.makeText(getContext(), "Abriendo Facebook", Toast.LENGTH_SHORT).show();
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
                            txtMonto = getView().findViewById(R.id.txtMonto);
                            txtMonto.setText("Precios: S/."+jsonObj2.getString("precio_producto"));
                            txtSede = getView().findViewById(R.id.txtSede);
                            txtSede.setText(jsonObj2.getString("nombre_sede"));
                            String im_producto = jsonObj2.getString("imagen_producto");
                            img_producto.setImageResource(map2.get(im_producto));
                            idproducto = Integer.parseInt(jsonObj2.getString("id_producto"));
                        }
                    }
                })
        );
        return view;
    }

    private void realizarCompra(Integer idusuario, Integer cantidadText, String mensajeText) {
        compositeDisposable.add(myAPI.registroCompra(cantidadText,mensajeText,idproducto,idusuario)
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
                            String im_producto = jsonObj2.getString("imagen_producto");
                            img_producto.setImageResource(map2.get(im_producto));

                        }
                    }
                })
        );
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

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
