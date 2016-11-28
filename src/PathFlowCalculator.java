/**
 * Created by mohamedGhribi on 11/26/16.
 */

/*This class wil print the Load matrix "L"
I made a new class to avoid the interfering of the results in the old class
Besfore using this class you need to pass the output of the first class "FloydWarshall.java"
Or you can just copy the output and paste it here as I did

 */
public class PathFlowCalculator {

    static final int N = 6;

    private final int[][][] pathPoints = {
            { { 0 }, { 0, 1 }, { 0, 3, 2 }, { 0, 3 }, { 0, 3, 2, 4 }, {0, 5 } },
            { { 1, 2, 4, 0 }, { 1 }, { 1, 2 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 5  } },
            { { 2, 4, 0 }, { 2, 4, 1 }, { 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 } },
            { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3 }, { 3, 2, 4}, { 3, 1, 5 } },
            { { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4 }, { 4, 1, 5 } },
            { {  5, 2, 4, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 2, 4 }, { 5 } }
    };
    private final int[][] F = {
            {0, 9, 11, 12, 8, 12},
            {18, 0, 15, 10, 17, 18},
            {17, 18, 0, 14, 10, 10},
            {17, 8, 10, 0, 17, 18},
            {15, 9, 12, 14, 0, 16},
            {18, 16, 15, 8, 9, 0}
    };

    // this is where we store results
    private final int[][] flowPerStep = new int[N][N];

    public PathFlowCalculator() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] currentPath = pathPoints[i][j];
                int currentFlow = F[i][j];
                for (int k = 0; k < currentPath.length - 1; k++) {
                    int fromNode = currentPath[k];
                    int toNode = currentPath[k + 1];
                    flowPerStep[fromNode][toNode] += currentFlow;
                }
            }
        }
    }

    //This is the main method

    public static void main(String... argv) {

        //Declaring couple of arrays to be used inside this function to store the results in
        //So that I can pass the results later
        long startTime  = System.currentTimeMillis();

        PathFlowCalculator calc = new PathFlowCalculator();
        for (int o=0; o<N; o++){
            for(int p=0;p<N;p++){
                System.out.print(calc.flowPerStep[o][p]+ "     ");
            }
            System.out.println();

            //The same function I used in FloydWarshall.java to compute the CPU usage and load

        }

    }

}