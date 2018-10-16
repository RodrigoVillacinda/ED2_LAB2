package S_DES;

import java.util.function.BinaryOperator;

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

    //paso6 opXORk1
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

    private int[] S0(){
        int[] s0 = new int[2];
        int[] opxork1 = new int[8];
        int row=0;
        int column=0;

        int roww=0;
        int columnn=0;

        opxork1[0] = OPXORK1()[0];
        opxork1[1] = OPXORK1()[1];
        opxork1[2] = OPXORK1()[2];
        opxork1[3] = OPXORK1()[3];

        int[][] box0=new int[4][4];
        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        int o1 = opxork1[0];
        int o2 = opxork1[3];

        row = (o1*2) + (o2*1); //2


        int i1 = opxork1[1];
        int i2 = opxork1[2];

        column =(i1*2) + (i2*1); //3

        roww = box0[row][column]; //00

        char[] rowColumn = Integer.toString(roww).toCharArray();

        for (int i=0; i<3; i++){

            columnn=rowColumn[i];
            s0[i] = columnn;

        }

        return  s0;
    }

    private int[] S1(){
        int[] s0 = new int[2];
        int[] opxork1 = new int[8];
        int row=0;
        int column=0;

        int roww=0;
        int columnn=0;

        opxork1[0] = OPXORK1()[4];
        opxork1[1] = OPXORK1()[5];
        opxork1[2] = OPXORK1()[6];
        opxork1[3] = OPXORK1()[7];

        int[][] box0=new int[4][4];
        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        int o1 = opxork1[0];
        int o2 = opxork1[3];

        row = (o1*2) + (o2*1); //2


        int i1 = opxork1[1];
        int i2 = opxork1[2];

        column =(i1*2) + (i2*1); //3

        roww = box0[row][column]; //00

        char[] rowColumn = Integer.toString(roww).toCharArray();

        for (int i=0; i<3; i++){

            columnn=rowColumn[i];
            s0[i] = columnn;

        }

        return  s0;
    }

    private int[] S0andS1(){
        int[] s0ands1 = new int[4];

        s0ands1[0] = S0()[0];
        s0ands1[1] = S0()[1];
        s0ands1[2] = S0()[0];
        s0ands1[3] = S0()[1];

        return s0ands1;
    }

    //PASO7: permutación de 4 bits, sobre la union de la box0 y box1
    private int[] FourPermutations(){
        int[] fourpermutations= new int[4];

        fourpermutations[0] = S0andS1()[1]; //2
        fourpermutations[1] = S0andS1()[3]; //4
        fourpermutations[2] = S0andS1()[2]; //3
        fourpermutations[3] = S0andS1()[0]; //1

        return fourpermutations;
    }

    //PASO8: Paso3 XOR paso 7
    private int[] LeftbitsXORFourpermutations(){
        int[] leftbitsXORfourpemutations = new int[4];

        leftbitsXORfourpemutations[0] = LeftBits()[0] ^ FourPermutations()[0];
        leftbitsXORfourpemutations[1] = LeftBits()[1] ^ FourPermutations()[1];
        leftbitsXORfourpemutations[2] = LeftBits()[2] ^ FourPermutations()[2];
        leftbitsXORfourpemutations[3] = LeftBits()[3] ^ FourPermutations()[3];

        return leftbitsXORfourpemutations;
    }

    


}
