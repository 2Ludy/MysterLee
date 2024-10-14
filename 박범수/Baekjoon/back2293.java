import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class back2293 {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 동전의 수
        int target = Integer.parseInt(st.nextToken()); // 전체 합
        int[] coefficients = new int[N]; // 동전의 종류를 저장하기 위한 배열

        for (int i = 0; i < N; i++) {
            coefficients[i]=Integer.parseInt(br.readLine()); // 동전의 종류 or 계수
        }
        // 로직
        // target = coeff1*x_1 + coeff2*x_2 + ... + coeff100*x_100 -> 최대의 경우의 선형방정식
        int[] result = new int[target+1]; // 각각의 coeff 에 대한 값을 기억하기 위한 배열
        result[0]=1; // 0 이 될 경우는 한가지
        for (int coeff: coefficients) {
            for (int i = coeff; i <= target; i++) {
                result[i]+= result[i-coeff]; // readme.md 참고
            }
        }
        System.out.println(result[target]);
    }
}
