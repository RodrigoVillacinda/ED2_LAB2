package S_DES;

public class CifradoSDES {

    private int Key[];
    private String Text;

    public CifradoSDES(int[] key, String text) {
        Key = key;
        Text = text;
    }

    //Seleccionar clave: 1010000010
    //Entrada: 1,2,3,4,5,6,7,8,9,10
    private int[] TenPermutations(){

        int tenpermutations[] = new int[10];
        int key[] = Key;

        tenpermutations[2] = key[0]; //3
        tenpermutations[4] = key[1]; //5
        tenpermutations[1] = key[2]; //2
        tenpermutations[6] = key[3]; //7
        tenpermutations[3] = key[4]; //4
        tenpermutations[9] = key[5]; //10
        tenpermutations[0] = key[6]; //1
        tenpermutations[8] = key[7]; //9
        tenpermutations[7] = key[8]; //8
        tenpermutations[5] = key[9]; //6


        return tenpermutations;
        //Salida: 3,5,2,7,4,10,1,9,8,6
    }

}
