package com.example.recycleit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.StringCharacterIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FragmentTransaction transaction;
    Fragment mainFragment;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button btnIniciarSesion,btnAtrasReciclaje,
            btnNewSoli,btnAdelanteReciclaje,btnEntrarSesion,
            btnRegistrarse,btnEntrarInvitado,btnDenunciarPunto
            ,btnRegistrarUsuario;
    private ImageButton btnPorPunto,btnPorRecolector;
    //comentario 2 qlo sdfgsfsdfsfd



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.Contenedor, mainFragment).commit();
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
        btnIniciarSesion = (Button) contactPopupView.findViewById(R.id.btnIniciarSesion);
        //Codigo layout popup
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
        //botones
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                lanzarVistaInicioSesion();
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
                dialog.dismiss();
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
                dialog.dismiss();
                msn("Registrado");
            }

        });

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

