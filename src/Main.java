/**
 * Created by mohamedgGhribi on 11/17/16.
 */


/*This class wil print the congestion matrix "G"
I made a new class to avoid the interfering of the results in the old class Besfore using this class you need to pass the output of the first class "FloydWarshall.java"
Or you can just copy the output and paste it here as I did
*/
public class Main {

    final static int INF = 999999999;
    static final int N = 6;

    public static void main(String[] args) {

        int[][] E = {
                {0, 7, INF, 7, INF, 9},
                {INF, 0, 5, INF, 10, 3},
                {9, 10, 0, 8, 4, 6},
                {9, 4, 2, 0, INF, INF},
                {3, 5, 10, 10, 0, INF},
                {INF, 5, 8, 10, INF, 0}

        };

        int[][] W = {
                {0, 9, 0, 31, 0, 12},
                {0, 0, 60, 0, 0, 52},
                {0, 0, 0, 24, 132, 10},
                {17, 26, 46, 0, 0, 0},
                {68, 43, 12, 14, 0, INF},
                {0, 16, 42, 8, 0, 0}

        };

        int[][] C = {
                {0, 13, INF, 33, INF, 20},
                {INF, 0, 67, INF, 5, 55},
                {5, 5, 0, 32, 134, 17},
                {23, 34, 55, 0, INF, INF},
                {68, 47, 20, 14, 0, INF},
                {INF, 16, 44, 16, INF, 0}

        };

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

//Declaring some arrays to store the results and pass them
                double[][] G = new double[N][N]; double[][] Q = new double[N][N];
                double[][] A = new double[N][N];
                double[][] Z = new double[N][N];
                A[i][j] = C[i][j] + 1; Q[i][j] = A[i][j] - W[i][j];
                Z[i][j] = A[i][j] / Q[i][j]; G[i][j] = Z[i][j] * E[i][j];
                G[i][j] = Math.round(G[i][j] * 100.0) / 100.0;
                System.out.print(G[i][j]+ "    "); }
            System.out.println();

//The same function I used in FloydWarshall.java to compute the CPU usage
            long stopTime = System.currentTimeMillis();
           // long runTime = stopTime - startTime; System.out.println("Run Time" + runTime);
        }
    }
}
