package com.example.desenvolvimento.teste;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AtendimentoActivity extends Activity {

  /*  private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference ref = database.child("Atendimento");*/

    private ImageView botaoCamera;
    private Button btsair;
    private Button btregistrar;
    private AlertDialog.Builder dialog;
    Spinner spinner1, spinner2;
    //grupo e radio button, Edit Text
    private RadioGroup grupogenero, grupoavaliacao, grupopulso, gruporespiracao, grupohemorragia;
    private RadioButton generoescolhido, avaliacaoescolhida, pulsoescolhido, respiracaoescolhida, hemorragiaescolhida;
    private EditText obs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento);


        Atendimento atendimento = new Atendimento();
        //alimentando o Spinner perfil
        List<String> opcao = new ArrayList<String>();
        opcao.add("Selecione Uma Opção...");
        opcao.add("Criança");
        opcao.add("Adolescente");
        opcao.add("Adulto");
        opcao.add("Idoso");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcao);

        spinner1 = (Spinner) findViewById(R.id.spnopcao);
        spinner1.setAdapter(adapter);

        //alimentando o Spinner ocorrência


        List<String> opcao2 = new ArrayList<String>();
        opcao2.add("Selecione Uma Opção...");
        opcao2.add("Acidente de Trânsito");
        opcao2.add("Agressão Física");
        opcao2.add("Envenenamento");
        opcao2.add("Afogamento");
        opcao2.add("Urgência Psiquiátrica");
        opcao2.add("Queimadura");
        opcao2.add("Trauma");
        opcao2.add("Mal Súbito");
        opcao2.add("Fratura/Entorses");
        opcao2.add("Falso Chamado");
        opcao2.add("Outros");
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, opcao2);

        spinner2 = (Spinner) findViewById(R.id.spnocorrencia);
        spinner2.setAdapter(adapter2);


        botaoCamera = (ImageView) findViewById(R.id.btnanexar);


        //radios grupos
        grupogenero = (RadioGroup) findViewById(R.id.rdgrupgenero);
        grupoavaliacao = (RadioGroup) findViewById(R.id.rdgrupoavaliacao);
        grupopulso = (RadioGroup) findViewById(R.id.rdgrupopulso);
        gruporespiracao = (RadioGroup) findViewById(R.id.rdgruporespiracao);
        grupohemorragia = (RadioGroup) findViewById(R.id.rdgrupohemorragia);


        //btsair
        btsair = (Button) findViewById(R.id.btsair);
        btsair.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          dialog = new AlertDialog.Builder(AtendimentoActivity.this);
                                          dialog.setTitle("Cancelar Registro e Sair da Aplicação");
                                          dialog.setMessage("Tem Certeza que Quer Sair ?");
                                          dialog.setCancelable(false);
                                          dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {

                                              }
                                          });
                                          dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                              @Override
                                              public void onClick(DialogInterface dialog, int which) {
                                                  Intent it = new Intent(getApplicationContext(), MainActivity.class);
                                                  it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                  it.putExtra("SAIR", true);
                                                  startActivity(it);

                                              }
                                          });
                                          dialog.create();
                                          dialog.show();

                                      }

                                  }
        );


        //btregistrar
        btregistrar = (Button) findViewById(R.id.btfinalizar);
        btregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Confirmacao();


            }
        });

        botaoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File picsDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File imageFile = new File(picsDir, "foto.jpg");

                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivity(i);
            }
        });


    }

    private void Confirmacao() {


        //Intent i = getIntent();
        // tipo = i.getStringExtra("tipo");
        // if(tipo!=null && !tipo.equals(""))  {

        dialog = new AlertDialog.Builder(AtendimentoActivity.this);
        dialog.setTitle("Registrar Atendimento");
        dialog.setMessage("Tem Certeza que quer Registrar o Atendimento ?");
        dialog.setCancelable(false);
        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Atendimento atendimento = new Atendimento();
                atendimento.setGenero(Genero());
                atendimento.setAvaliacao(Avaliacao());
                atendimento.setRespiracao(Respiracao());
                atendimento.setPulso(Pulso());
                atendimento.setHemorragia(Hemorragia());
                atendimento.setObs(Obs());
                atendimento.setDataini(Dataini());
                atendimento.setTpocorrecia(Tpocorrencia());
                atendimento.setPerfil(Perfil());


                //registra no banco de dados
                       /* String key = ref.push().getKey();
                        ref.child(key).setValue(atendimento); */
                String chave = Banco.Inserir(atendimento);


                Toast.makeText(AtendimentoActivity.this, "Atendimento Registrado ", Toast.LENGTH_SHORT).show();

                Intent inte = new Intent(AtendimentoActivity.this, MainActivity.class);
                inte.putExtra("datafin", chave);
                startActivity(inte);

            }
        });
        dialog.create();
        dialog.show();
    }


    private String Genero() {
        String genero = "";
        int idgenero = grupogenero.getCheckedRadioButtonId();
        if (idgenero > 0) {
            generoescolhido = (RadioButton) findViewById(idgenero);
            genero = (generoescolhido.getText()).toString();
        }
        return genero;
    }

    private String Avaliacao() {
        String avaliacao = "";
        int idavaliacao = grupoavaliacao.getCheckedRadioButtonId();
        if (idavaliacao > 0) {
            avaliacaoescolhida = (RadioButton) findViewById(idavaliacao);
            avaliacao = (avaliacaoescolhida.getText()).toString();
        }
        grupoavaliacao.clearCheck();
        return avaliacao;
    }

    private String Respiracao() {
        String respiracao = "";
        int idrespiracao = gruporespiracao.getCheckedRadioButtonId();
        if (idrespiracao > 0) {
            respiracaoescolhida = (RadioButton) findViewById(idrespiracao);
            respiracao = (respiracaoescolhida.getText()).toString();
        }
        gruporespiracao.clearCheck();
        return respiracao;
    }

    private String Pulso() {
        String pulso = "";
        int idpulso = grupopulso.getCheckedRadioButtonId();
        if (idpulso > 0) {
            pulsoescolhido = (RadioButton) findViewById(idpulso);
            pulso = (pulsoescolhido.getText()).toString();
        }
        grupopulso.clearCheck();
        return pulso;
    }

    private String Hemorragia() {
        String hemorragia = "";
        int idhemorragia = grupohemorragia.getCheckedRadioButtonId();
        if (idhemorragia > 0) {
            hemorragiaescolhida = (RadioButton) findViewById(idhemorragia);
            hemorragia = (hemorragiaescolhida.getText()).toString();
        }
        grupohemorragia.clearCheck();
        return hemorragia;
    }

    private String Obs() {

        esconderTeclado();
        String observacao = "";
        obs = (EditText) findViewById(R.id.obs);

        observacao = (obs.getText()).toString();
        obs.setText("");


        return observacao;


    }

    private String Perfil() {

        String perfil = (String) spinner1.getSelectedItem();

        return perfil;
    }

    private String Tpocorrencia() {

        String ocorrencia = (String) spinner2.getSelectedItem();

        return ocorrencia;
    }

    private String Dataini() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date data = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date data_atual = cal.getTime();

        String data_completa = dateFormat.format(data_atual);

        return data_completa;
    }

    public void esconderTeclado() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }


}