package S_DES;

public class CifradoSDES {

    private int Key[];

    public CifradoSDES(int[] key) {
        Key = key;
    }


    private int[] EightPermutations(){
        int eightpermutations[] = new int[8];

        eightpermutations[0] = Key[1]; //2
        eightpermutations[1] = Key[5]; //6
        eightpermutations[2] = Key[2]; //3
        eightpermutations[3] = Key[0]; //1
        eightpermutations[4] = Key[3]; //4
        eightpermutations[5] = Key[7]; //8
        eightpermutations[6] = Key[5]; //5
        eightpermutations[7] = Key[6]; //7

        return eightpermutations;
    }



}
