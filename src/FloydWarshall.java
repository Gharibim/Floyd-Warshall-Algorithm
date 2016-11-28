/**
 * Created by mohamedgGhribi on 11/17/16.
 */

import java.util.*;

public class FloydWarshall {

    //Declaring some variables for the negative cycle such as INF to a very big number
    //Declaring couple of arrays to store the results so that we could use them to print the output
    final static int INF = 99999;
    static int[][] P;
    static final int N = 6;
    static int[][] O;

    // Main method
    public static void main(String[] args) {
        //Function to calculate the CPU load and usage starting from here in milliseconds
        long startTime  = System.currentTimeMillis();

        // Arrays to use them inside the functions
        int[][][] pathPoints = new int[N][N][];
        int[][] hops = new int[N][N];
        final int[][] flowPerStep = new int[N][N];
        double[][] G = new double[N][N];
        double [][] Q = new double[N][N];
        double [][] A = new double[N][N];
        double [][] Z = new double[N][N];

        P = new int[N][N];
        int[][] E = {
                {0, 7, INF, 7, INF, 9},
                {INF, 0, 5, INF, 10, 3},
                {9, 10, 0, 8, 4, 6},
                {9, 4, 2, 0, INF, INF},
                {3, 5, 10, 10, 0, INF},
                {INF, 5, 8, 10, INF, 0}

        };

        int[][] F = {
                {0, 9, 11, 12, 8, 12},
                {18, 0, 15, 10, 17, 18},
                {17, 18, 0, 14, 10, 10},
                {17, 8, 10, 0, 17, 18},
                {15, 9, 12, 14, 0, 16},
                {18, 16, 15, 8, 9, 0}

        };



        int[][] C = {
                {0, 13, INF, 33, INF, 20},
                {INF, 0, 67, INF, 5, 55},
                {5, 5, 0, 32, 134, 17},
                {23, 34, 55, 0, INF, INF},
                {68, 47, 20, 14, 0, INF},
                {INF, 16, 44, 16, INF, 0}

        };




        //Prining the matrices we have by calling the printMatrix function
        System.out.println("Adjacency Matrix E with Edge-Weights");
        printMatrix(E);
        System.out.println("All-Pairs-Shortest-Paths for a Single Car");
        printMatrix(Floyd(E));
        System.out.println("Path Matrix");
        printMatrix(P);
        System.out.println("Adjacency Matrix F with Flow");
        printMatrix(F);








        // two loops to print the path matrix
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                pathPoints[i][j] = pathD(i, j, P) ;
                hops[i][j] = pathPoints[i][j].length - 1;

            }
        }


        //Controlling the path matrix using another two loops
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {


                System.out.print("(");
                for (int n = 0; n < pathPoints[i][j].length; n++)
                    System.out.print(pathPoints[i][j][n]);

                System.out.print(")");



            }

            System.out.println("\n");

        }

        //Printing out the hop count matrix by calling the printMatrix function
        System.out.println("hops count");
        printMatrix(hops);

        //The rest of the function to compute the CPU usage and load
        long stopTime = System.currentTimeMillis();
        long runTime = stopTime - startTime;
        System.out.println("Run Time" + runTime);


    }







    // Defining enfinity in the matrix
    public static int[][] Floyd(int[][] M) {
        final int Inf = 10000;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || M[i][j] == Inf)
                    P[i][j] = -1;
                else P[i][j] = i;
            }


        }


        //  Shortest path algorithm Floyd Warshall
        for (int k = 0; k < N; k++) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (M[i][k] + M[k][j] < M[i][j]) {

                        M[i][j] = M[i][k] + M[k][j];
                        P[i][j] = P[k][j];
                    } else {
                        M[i][j] = M[i][j];
                    }
                }

            }
        }


        return M;
    }


    //Getting the Path matrix
    public static int[] pathD(int i, int j, int[][] p) {

        //Declaring a stack to implement DFS algorithm
        Stack<Integer> ps = new Stack<Integer>();
        ps.push(j);
        int temp = p[i][j];

        //loop to reverse the stack
        while (temp != -1) {
            {
                ps.push(temp);
                temp = p[i][temp];
            }
        }

        //Declaring an array to store the output of the stack after reversing
        int[] arr = new int[ps.size()];





        // poping an element from the stack in each loop
        int k = 0;
        while (!ps.empty()){
            arr[k] = ps.pop();
            k++;
        }
        //The returning array from the function
        return arr;

    }



    // Printing as a matrix function for the integer arrays
    public static void printMatrix(int[][] Matrix) {
        System.out.print("\n\t");
        for (int j = 0; j < N; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        for (int j = 0; j < 50; j++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(i + " |\t");
            for (int j = 0; j < N; j++) {
                if (Matrix[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(Matrix[i][j]);
                System.out.print("\t");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }



//Function to print as a matrix but this function accepts the double type arrays
    public static void printMatrixx(double[][] Matrix) {
        System.out.print("\n\t");
        for (int j = 0; j < N; j++) {
            System.out.print(j + "\t");
        }
        System.out.println();
        for (int j = 0; j < 50; j++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < N; i++) {
            System.out.print(i + " |\t");
            for (int j = 0; j < N; j++) {
                if (Matrix[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(Matrix[i][j]);
                System.out.print("\t");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }



}



