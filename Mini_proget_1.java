package exojava;

public class Mini_proget_1 {

    public static void main(String[] args) {
        boolean mois= false;
        short count=0;
        while(mois){
            System.out.print(count+"\n");
            count++;
            if (count>=10){
                mois= false;
            }
        }
        System.out.print("coucou");
    }
}
