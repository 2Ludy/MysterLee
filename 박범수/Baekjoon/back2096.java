import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back2096 {
    static int N, min, max;
    static int[][] data;
    static int[][] data_copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        data = new int[N][3];// 최대값 갱신을 위한 배열
        data_copy = new int[N][3]; //최소값 갱신을 위한 배열
        // 입략
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = data_copy[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = data_copy[i][1] = Integer.parseInt(st.nextToken());
            data[i][2] = data_copy[i][2] = Integer.parseInt(st.nextToken());
        }
        // 최소값 구하기
        for (int i = 0; i < N - 1; i++) {
            // 왼
            int a = data_copy[i + 1][0] + data_copy[i][0];
            int b = data_copy[i + 1][0] + data_copy[i][1];
            data_copy[i + 1][0] = Math.min(a, b); // 왼쪽 값을 갱신
            // 중
            int c = data_copy[i + 1][1] + data_copy[i][0];
            int d = data_copy[i + 1][1] + data_copy[i][1];
            int e = data_copy[i + 1][1] + data_copy[i][2];
            data_copy[i + 1][1] = Math.min(c, Math.min(d, e)); // 가운데 값을 갱신
            // 오
            int f = data_copy[i + 1][2] + data_copy[i][1];
            int g = data_copy[i + 1][2] + data_copy[i][2];
            data_copy[i + 1][2] = Math.min(f, g); // 오른쪽 값을 갱신
        }
        min = Integer.MAX_VALUE; // 정수형 최대값으로 초기화
        for (int i = 0; i < 3; i++) {
            if (min > data_copy[N - 1][i]) min = data_copy[N - 1][i];
        }
        // 최대값 구하기
        for (int i = 0; i < N - 1; i++) {
            // 왼
            int a = data[i + 1][0] + data[i][0];
            int b = data[i + 1][0] + data[i][1];
            data[i + 1][0] = Math.max(a, b); // 왼쪽 값을 갱신
            // 중
            int c = data[i + 1][1] + data[i][0];
            int d = data[i + 1][1] + data[i][1];
            int e = data[i + 1][1] + data[i][2];
            data[i + 1][1] = Math.max(c, Math.max(d, e)); // 가운데 값을 갱신
            // 오
            int f = data[i + 1][2] + data[i][1];
            int g = data[i + 1][2] + data[i][2];
            data[i + 1][2] = Math.max(f, g); // 오른쪽 값을 갱신
        }
        max = 0; // 최소값으로 초기화
        for (int i = 0; i < 3; i++) {
            if (max < data[N - 1][i]) max = data[N - 1][i];
        }
        System.out.println(max + " " + min);
    }
}
