package com.example.recycleit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentTransaction transaction;
    Fragment mainFragment;
    private EditText etxtNombre, etxtCorreo, etxtPassword, etxtPasswordV,etxtNumeroT,editTextTextPersonName,editTextTextPassword;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button btnIniciarSesion,btnAtrasReciclaje,
            btnNewSoli,btnAdelanteReciclaje,btnEntrarSesion,
            btnRegistrarse,btnEntrarInvitado,btnDenunciarPunto
            ,btnRegistrarUsuario, btnVolverPerfil;
    private ImageButton btnPorPunto,btnPorRecolector;
    //comentario34433

    private TextView tvNombreUser,tvCorreoUser,tvTelefonoUser,tvNombreReco1,tvNombreReco2,tvNombreReco3,tvTelefonoReco1,tvTelefonoReco2,tvTelefonoReco3;

    //Datos para el perfil
    private String nombre = "sin informacion";
    private String correo = "";
    private String numeroT = "";
    private int idUser= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.Contenedor, mainFragment).commit();
        TextView tvNombreUser;
        System.out.println("HolaAAA");
        //tvNombreUser = (TextView)findViewById(R.id.tvNombreUser);
        System.out.println("HolaAAA");


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show();
            Perfil();
        }
        if (id == R.id.item2) {
            Toast.makeText(this, "solicitar nuevo punto", Toast.LENGTH_SHORT).show();
            newPunto();
        }
        if (id == R.id.item3) {
            Toast.makeText(this, "denunciar punto", Toast.LENGTH_SHORT).show();
            denunciarPunto();
        }

        return super.onOptionsItemSelected(item);

    }

    //Popup Menu
    public void Perfil() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popupperfil, null);
        //Codigo layout popup
        btnIniciarSesion = (Button)contactPopupView.findViewById(R.id.btnIniciarSesion);
        btnVolverPerfil = (Button)contactPopupView.findViewById(R.id.btnVolverPerfil);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();

        //Agregar info al perfil si existe
        System.out.println("antes de ");
        tvNombreUser = (TextView)contactPopupView.findViewById(R.id.tvNombreUser);
        tvCorreoUser = (TextView)contactPopupView.findViewById(R.id.tvCorreoUser);
        tvTelefonoUser = (TextView)contactPopupView.findViewById(R.id.tvTelefonoUser);

        tvNombreUser.setText(nombre);
        tvCorreoUser.setText(correo);
        tvTelefonoUser.setText(numeroT);
        System.out.println("Despues de ");

        dialog.show();
        //botones
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                lanzarVistaInicioSesion();
            }
        });

        btnVolverPerfil.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dialog.dismiss();
            }
        });

    }
    public void newPunto() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.solicitarnuevopunto, null);
        //Codigo layout popup
        btnNewSoli = (Button) contactPopupView.findViewById(R.id.btnNewSoli);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //botones
        btnNewSoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                msn("Solicitud Enviada :)");
            }

        });

    }
    public void denunciarPunto() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.denunciarpunto, null);
        //Codigo layout popup
        btnDenunciarPunto = (Button) contactPopupView.findViewById(R.id.btnAceptarDenunciarPunto);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //botones
        btnDenunciarPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                msn("Punto Denunciado.");
            }
        });

    }

    //Popup Reciclar
    public void BtnReciclaje() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popupreciclaje, null);
        //Codigo layout popup
        btnAtrasReciclaje = (Button) contactPopupView.findViewById(R.id.btnAtrasR);
        btnAdelanteReciclaje = (Button) contactPopupView.findViewById(R.id.btnReciclajeS);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //botones
        btnAtrasReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnAdelanteReciclaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                BtnReciclajeTipo();
            }
        });

    }
    public void BtnReciclajeTipo() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popupreciclajeforma, null);
        //Codigo layout popup
        btnPorPunto= (ImageButton) contactPopupView.findViewById(R.id.btnReciclajePorPunto);
        btnPorRecolector= (ImageButton) contactPopupView.findViewById(R.id.btnReciclajePorRecolector);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //botones
        btnPorPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnPorRecolector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                lanzarVistaRecolectores();
            }
        });
    }
    //lanzarVistaLayout
    public void lanzarVistaRecolectores() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.listarecolectores, null);

        tvNombreReco1 = (TextView)contactPopupView.findViewById(R.id.tvNombreReco1);
        tvTelefonoReco1 = (TextView)contactPopupView.findViewById(R.id.tvTelefonoReco1);
        tvNombreReco2 = (TextView)contactPopupView.findViewById(R.id.tvNombreReco2);
        tvTelefonoReco2 = (TextView)contactPopupView.findViewById(R.id.tvTelefonoReco2);
        tvNombreReco3 = (TextView)contactPopupView.findViewById(R.id.tvNombreReco3);
        tvTelefonoReco3 = (TextView)contactPopupView.findViewById(R.id.tvTelefonoReco3);

        Recolector reco1 = new Recolector("Pepe Mujica","978787563");
        Recolector reco2 = new Recolector("Donald Trump","933787563");
        Recolector reco3 = new Recolector("Vladimir Putin","944787564");
        Recolector reco4 = new Recolector("Beto Fernandez","955787565");
        Recolector reco5 = new Recolector("Jair Bolsonaro","966787566");
        Recolector reco6 = new Recolector("Elva Surita","966777766");

        Map<Integer,Recolector> recolectores = new HashMap<Integer, Recolector>();
        recolectores.put(1,reco1);
        recolectores.put(2,reco2);
        recolectores.put(3,reco3);
        recolectores.put(4,reco4);
        recolectores.put(5,reco5);
        recolectores.put(6,reco5);

        int numero1 = (int)(Math.random()*6+1);
        Recolector recolector1 = recolectores.get(numero1);
        int numero2 = (int)(Math.random()*6+1);
        Recolector recolector2 = recolectores.get(numero2);
        int numero3 = (int)(Math.random()*6+1);
        Recolector recolector3 = recolectores.get(numero3);

        tvNombreReco1.setText(recolector1.getNombre());
        tvTelefonoReco1.setText(recolector1.getTelefono());
        tvNombreReco2.setText(recolector2.getNombre());
        tvTelefonoReco2.setText(recolector2.getTelefono());
        tvNombreReco3.setText(recolector3.getNombre());
        tvTelefonoReco3.setText(recolector3.getTelefono());

        //Codigo layout popup
;
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }
    public void lanzarVistaInicioSesion() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.iniciarsesion, null);
        //Codigo layout popup
        btnEntrarSesion=(Button)contactPopupView.findViewById(R.id.ISbtnEntrar);
        btnRegistrarse=(Button)contactPopupView.findViewById(R.id.ISbtnRegistrarse);
        btnEntrarInvitado=(Button)contactPopupView.findViewById(R.id.ISbtnEntrarComoInvitado);

        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();


        btnEntrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recurar valor de EditText
                editTextTextPersonName = (EditText)contactPopupView.findViewById(R.id.editTextTextPersonName);
                editTextTextPassword = (EditText)contactPopupView.findViewById(R.id.editTextTextPassword);
                String correoUser = editTextTextPersonName.getText().toString();
                String password = editTextTextPassword.getText().toString();

                if(buscarUserValido(correoUser, password)){
                    dialog.dismiss();
                    Perfil();
                    msn("User Valido bienvenido");
                    //########################################

                }else{
                    System.out.println("correo- "+ correoUser);
                    System.out.println("pass- "+ password);
                    msn("User no Valido");
                }

            }
        });
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                lanzarVistaRegistrar();
                
                //Lanzar layout de registro
            }
        });
        btnEntrarInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public boolean buscarUserValido(String correoUser, String password){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor datoObtenido =  db.rawQuery("select * from Usuario where Email = '"+correoUser+"' and Password ='"+password+"'",null);
        System.out.println("correo- "+ correoUser);
        System.out.println("pass- "+ password);

        if(datoObtenido.moveToFirst()){
            idUser = datoObtenido.getInt(0);
            System.out.println("·················");

            nombre = datoObtenido.getString(1);
            correo = datoObtenido.getString(3);
            numeroT = datoObtenido.getString(4);

            editTextTextPersonName.setText("");
            editTextTextPassword.setText("");
            Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show();
            db.close();
            return true;
        }else{
            Toast.makeText(this,"Usuario No Registrado",Toast.LENGTH_SHORT).show();
            db.close();
            return false;
        }

    }

    public void lanzarVistaRegistrar() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.registrarusuario, null);
        //Codigo layout popup
        btnRegistrarUsuario = (Button) contactPopupView.findViewById(R.id.btnAddUser);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //botones
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etxtNombre = (EditText)contactPopupView.findViewById(R.id.etxtNombre);
                etxtCorreo = (EditText)contactPopupView.findViewById(R.id.etxtCorreo);
                etxtPassword = (EditText)contactPopupView.findViewById(R.id.etxtPassword);
                etxtPasswordV = (EditText)contactPopupView.findViewById(R.id.etxtPasswordV);
                etxtNumeroT = (EditText)contactPopupView.findViewById(R.id.etxtNumeroT);

                if(registrarUser()){
                    System.out.println("dentro de if");
                    msn("Registrado exitosamente");
                    dialog.dismiss();
                    lanzarVistaInicioSesion();

                }else{
                    System.out.println("dentro del else");
                    msn("No valida tus datos");
                }

            }

        });

    }

    public void limpiarCampos(){
        etxtNombre.setText("");
        etxtCorreo.setText("");
        etxtPassword.setText("");
        etxtPasswordV.setText("");
        etxtNumeroT.setText("");
    }

    public boolean registrarUser(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //validamso que los campos no se encuentren vacios
        if(etxtNombre.getText().toString().equals("") || etxtCorreo.getText().toString().equals("") || etxtPassword.getText().toString().equals("") || etxtPasswordV.getText().toString().equals("") || etxtNumeroT.getText().toString().equals("") ){

            System.out.println("1- "+etxtNombre.getText().toString());
            System.out.println("2- "+etxtCorreo.getText().toString());
            System.out.println("3- "+etxtNumeroT.getText().toString());
            System.out.println("4- "+etxtPassword.getText().toString());
            System.out.println("5- "+etxtPasswordV.getText().toString());

            //Toast.makeText(this,"Hay Campos vacios",Toast.LENGTH_SHORT).show();
            msn("Hay Campos vacios");
            System.out.println("Hay Campos vacios");
            return false;

        }else{
            //Validamos que ambas contraseñas sean validas
            if(etxtPassword.getText().toString().equals(etxtPasswordV.getText().toString())){
                ContentValues reg = new ContentValues();
                reg.put("Nombre",etxtNombre.getText().toString());
                reg.put("Password", etxtPassword.getText().toString());
                reg.put("Email",etxtCorreo.getText().toString());
                reg.put("Telefono",etxtNumeroT.getText().toString());

                //insertamos los datos en la tabla usuario
                long d = db.insert("Usuario", null,reg);
                System.out.println("insert = " + d);
                db.close();

                Toast.makeText(this,"Tu Cuenta se creo exitosamente",Toast.LENGTH_SHORT).show();
                //limpiamos los campos y lo llevamos a la pagina de registro para que inicie sesion
                limpiarCampos();

            }else{

                Toast.makeText(this,"Verifica que la contraseña coincida",Toast.LENGTH_SHORT).show();
                System.out.println("Verifica que la contraseña coincida");
                return false;

            }
            return true;
        }


    }

    //Metodos
   public void reciclaje(View v){
        Toast.makeText(this, "Reciclaje", Toast.LENGTH_SHORT).show();
        BtnReciclaje();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Reciclaje", Toast.LENGTH_SHORT).show();
        BtnReciclaje();
    }
    public void reciclajeSiguiente(View v){
        Toast.makeText(this, "Forma de reciclar", Toast.LENGTH_SHORT).show();
        BtnReciclajeTipo();
    }
    public void msn(String msn){
        Toast.makeText(this, msn, Toast.LENGTH_SHORT).show();

    }

}






    /**Menu Mapa
    public void Mapa(){
        Intent contacto = new Intent(this,MainActivity.class);
        startActivity(contacto);
    }**/

