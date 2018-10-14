package S_DES;

import java.util.BitSet;

public class CifradoSDES {

    private int Key;
    private String Text;

    public CifradoSDES(int key, String text) {
        Key = key;
        Text = text;
    }

    //Seleccionar clave: 1010000010
    private void TenPermutations(){

        Byte ArrayBits[] = new Byte[10];
        Byte tenpermutations=  Byte.parseByte(Integer.toString(Key));

        

    }

}
