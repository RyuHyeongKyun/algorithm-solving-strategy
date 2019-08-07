import java.util.*;



public class Solution{

    static int[] board;

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        

        int TC = scan.nextInt(); //풀고자 하는 문제의 개수

        scan.nextLine();

        for(int i = 0; i < TC; i++){

            int cnt = scan.nextInt(); //각 문제에서 나무판자의 개수

            Solution.board = new int[cnt];

            for(int j = 0; j < cnt; j++){

             board[j] = scan.nextInt(); //각 문제에서 나무판자의 길이

            }

            int output = new Solution().solve(0, cnt-1); //최대 넓이

            System.out.println( output );

            

        }

        scan.close();

    }





public int solve(int left, int right){

    int area1, area2, area3; //세 가지 경우로 나누어서 생각해서 풀기

    int middle = (left + right) / 2; // 정수로 나와야 되기 때문에 분수이면 소수점은 버림

    

    if(right - left == 0){

     return board[left]; //울타리가 하나밖에 없을 떄

        

    }

    area1 = solve(left, middle); 

    area2 = solve(middle + 1, right); //울타리를 절반으로 나누어 왼쪽과 오른쪽에서 각각 최대 넓이를 구함

    

    int temp, tmp1, tmp2;

    int high; 

    tmp1 = middle;

    tmp2 = middle+1; //울타리 가운데 부분을 기준으로 

    

    high = Math.min(board[tmp1], board[tmp2]);

    temp = high * 2;

    

    tmp1--; 

    tmp2++; //각각 왼쪽,오른쪽으로 한 칸씩 이동

    

    while(true){

        if(tmp1 >= left && tmp2 <= right) //아직 tmp1과 tmp2가 왼쪽 오른쪽으로 갈 수 있는 상태

        {

            if(board[tmp1] > board[tmp2]){ //tmp1 높이가 tmp2 높이보다 크면 tmp1쪽으로 최대 직사각형을 넓히는게 유리

                high = Math.min(board[tmp1], high);

                temp = Math.max(temp, high * (tmp2 -tmp1)); //이전과 비교하여 더 높은 값을 지정

                tmp1--;  

            }

            else{  //tmp2 높이가 tmp1 높이보다 큰 경우

                high = Math.min(board[tmp2],high);

                temp = Math.max(temp, high * (tmp2 - tmp1));

                tmp2++;

                

            }

        }

        else if (tmp1 >= left){ //왼쪽밖에 늘릴 수 없는 경우

            high = Math.min(board[tmp1], high);

            temp = Math.max(temp, high * (tmp2 - tmp1));

            tmp1--;

        }

        else if(tmp2 <= right){ //오른쪽밖에 늘릴 수 없는 경우

            high = Math.min(board[tmp2],high);

            temp = Math.max(temp, high *(tmp2-tmp1));

            tmp2++;

            

        }

        else{

            break;

        }

                

        }

        area3 = temp;

        

        int max = area1;

        if(max < area2) max = area2;

        if(max < area3) max = area3; //가운데를 기준으로 왼쪽 울타리들의 최댓값, 오른쪽 울타리들의 최댓값

         //가운데 두개의 울타리를 포함했을 때의 최댓값을 비교하여 가장 큰 것 출력

        

        return max;

    }

    

//시간 복잡도 nlog_2(n)

}


