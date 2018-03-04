package com.example.desenvolvimento.teste;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Capitão on 07/04/2017.
 */

public class Banco {

    private static DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static DatabaseReference ref = database.child("Atendimento");


    public static String Inserir(Atendimento aten) {

        String chave = PegarChave();
        ref.child(chave).setValue(aten);

        return chave;
    }

    public static String PegarChave() {

        String key = ref.push().getKey();
        return key;
    }


}
