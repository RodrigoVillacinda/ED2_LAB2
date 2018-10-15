package S_DES;

public class CifradoSDES {

    private int Key[];
    private int K1[];
    private int k2[];

    public CifradoSDES(int[] key, int[] k1, int[] k2) {
        Key = key;
        K1 = k1;
        this.k2 = k2;
    }


    //paso2
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

    //paso3, dividir izquierda
    private int[] LeftBits(){

        int[] leftbits=new int[4];

        leftbits[0] = EightPermutations()[0];
        leftbits[1] = EightPermutations()[1];
        leftbits[2] = EightPermutations()[2];
        leftbits[3] = EightPermutations()[3];


        return leftbits;

    }

    //paso4, dividir derecha
    private int[] RightBits(){

        int[] righbits=new int[4];

        righbits[0] = EightPermutations()[0];
        righbits[1] = EightPermutations()[1];
        righbits[2] = EightPermutations()[2];
        righbits[3] = EightPermutations()[3];


        return righbits;

    }

    //paso5, expandir y pemutar, OP
    private int[] ExpandRigh(){
        int[] expandright= new int[8];

        expandright[0] = RightBits()[3]; //4
        expandright[1] = RightBits()[0]; //1
        expandright[2] = RightBits()[1]; //2
        expandright[3] = RightBits()[2]; //3
        expandright[4] = RightBits()[1]; //2
        expandright[5] = RightBits()[2]; //3
        expandright[6] = RightBits()[3]; //4
        expandright[7] = RightBits()[0]; //1


        return expandright;
    }

    private int[] OPXORK1(){
        int[] opxork1= new int[8];
        int[] k1=K1;

        opxork1[0] = ExpandRigh()[0] ^ k1[0];
        opxork1[1] = ExpandRigh()[1] ^ k1[1];
        opxork1[2] = ExpandRigh()[2] ^ k1[2];
        opxork1[3] = ExpandRigh()[3] ^ k1[3];
        opxork1[4] = ExpandRigh()[4] ^ k1[4];
        opxork1[5] = ExpandRigh()[5] ^ k1[5];
        opxork1[6] = ExpandRigh()[6] ^ k1[6];
        opxork1[7] = ExpandRigh()[7] ^ k1[7];

        return opxork1;
    }

}
