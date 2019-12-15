import java.util.Scanner;

public class Matrice2 {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Da-ti dimensiunea matricii patratice: ");
        int n = myObj.nextInt();
        int[][] m = new int[n][n];
        System.out.println();
        Citire(m,n,n,0,0);
        System.out.println();
        Afisare(m,n,n,0,0);
    }
    private static void Citire(int[][] m, int n1, int n2, int i, int j){
        Scanner myObj = new Scanner(System.in);
        if(i<n1-1){
            System.out.print("m["+i+"]["+j+"]=" );
            m[i][j]=myObj.nextInt();
            if(j<n2-1){
                Citire(m,n1,n2,i,j+1);
            }else if(j==n2-1) {
                j = 0;
                Citire(m, n1, n2, i+1, j);
            }
        }
    }
    private static void Afisare(int[][] m, int n1, int n2, int i, int j){
        if(i<n1-1){
            System.out.print(m[i][j] + " ");
            if(j<n2-1){
                Afisare(m,n1,n2,i,j+1);
            }else if(j==n2-1) {
                j = 0;
                System.out.println();
                Afisare(m, n1, n2, i+1, j);
            }
        }
    }
}
