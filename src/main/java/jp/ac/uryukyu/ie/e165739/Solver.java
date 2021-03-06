package jp.ac.uryukyu.ie.e165739;
import java.util.Scanner;

public class Solver {
    private static final int num=10,X=0,Y=1,EMPTY=0,BLACK=1,WHITE=2,DRAW=3,WALL=9;
    private static final int[][] dir = {{-1,-1}, { 0,-1}, { 1,-1}, { 1, 0}, { 1, 1}, { 0, 1}, {-1, 1}, {-1, 0}};
    private static int[][] board = new int[num][num];
    private static int x,y,turn=BLACK,victory ;
    private static final String RANGE="[1-8]";

    // 盤の黒、白、壁の初期設定
    public static void init(){
        for(int i=0;i<num;i++){
            board[0][i] = board[9][i] = WALL;
        }
        for(int i=1;i<num-1;i++){
            board[i][0] = board[i][9] = WALL;
        }
        board[4][4] = board[5][5] = WHITE;
        board[4][5] = board[5][4] = BLACK;
    }

    // 盤の表示
    public static void show(){
        // 外枠の番号づけ
        System.out.print("\n     ");
        for(int i=1;i<num-1;i++){
            System.out.print(i);
            if(i<num-2){
                System.out.print(" ");
            }
        }
        // 黒、白、壁、空白の判定・マークづけ
        for(int i=0;i<num;i++){
            if(0<i && i<num-1){
                System.out.printf("\n %d ",i);
            }else{
                System.out.print("\n   ");
            }
            for(int j=0;j<num;j++){
                // マークづけ
                switch(board[i][j]){
                    case WALL  : {
                        System.out.print("■");
                        break;
                    }case EMPTY : {
                        System.out.print(" ");
                        break;
                    }case WHITE : {
                        System.out.print("○");
                        break;
                    }case BLACK : {
                        System.out.print("●");
                    }
                }
                System.out.print(" ");
            }
            if(i<num-1){
                System.out.print(" ");
            }else{
                System.out.print("\n");
            }
        }
        System.out.print("\n--------------------------------------------------------------------\n");
    }

    // 順番の表示
    public static void black_white_turn(){
        if(turn==BLACK){
            System.out.print("\n-----黒番-----\n\n");
        }else if(turn==WHITE){
            System.out.print("\n-----白番-----\n\n");
        }
    }

    // 順番を入れ替える
    public static void shift(){
        turn = 3 - turn;
    }

    // 黒と白の個数を数える
    public static boolean judge(){
        int black = 0, white = 0;
        for(int i=1;i<num-1;i++){
            for(int j=1;j<num-1;j++){
                if(board[i][j]==BLACK){
                    black++;
                }else if(board[i][j]==WHITE){
                    white++;
                }
            }
        }
        // victoryを決定させる
        if(black+white==8*8){
            if(black<white){
                victory = WHITE;
            } else if(black==white){
                victory = DRAW;
            } else {
                victory = BLACK;
            }
            return true;
        }
        return false;
    }



    // 以下3つのメソッドはそれぞれレポートで説明
    public static boolean update(){
        boolean ret;
        if(chk_cell()){
            flip();
            ret = true;
        }else{
            ret = false;
        }
        return ret;
    }

    public static boolean chk_cell(){
        if(board[y][x]!=EMPTY){
            return false;
        }
        boolean result = false;
        out:for(int i=0;i<dir.length;i++){
            int j=x,k=y;
            j += dir[i][X];
            k += dir[i][Y];
            if(board[k][j] == 3 - turn){
                while(true){
                    j += dir[i][X];
                    k += dir[i][Y];
                    if(board[k][j]==turn){
                        result = true;
                        break out;
                    }else if(board[k][j]== 3 - turn){
                        continue;
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static void flip(){
        board[y][x] = turn;
        for(int i=0;i<dir.length;i++){
            int j=x,k=y;
            j += dir[i][X];
            k += dir[i][Y];
            if(board[k][j] == 3 - turn){
                out:while(true){
                    j += dir[i][X];
                    k += dir[i][Y];
                    if(board[k][j]==turn){
                        while(true){
                            j -= dir[i][X];
                            k -= dir[i][Y];
                            if(board[k][j]==turn){
                                break out;
                            }
                            board[k][j] = turn;
                        }
                    }else if(board[k][j]== 3 - turn){
                        continue;
                    }
                    break;
                }
            }
        }
    }



    // 勝者の表示
    public static void show_result(){
        if (victory == DRAW) {
            System.out.println("\n\nこの勝負　引き分け！\n\n");
        }else if(victory == BLACK){
            System.out.println("\n\nこの勝負　黒　の勝ち！\n\n");
        }else{
            System.out.println("\n\nこの勝負　白　の勝ち！\n\n");
        }
        show(); // 最終的な盤面を表示
    }

    // プレイヤーが文字を入力
    public static Scanner sc = new Scanner(System.in);
    public static int input(){
        int ret = 0;
        while(true){
            String[] s = sc.nextLine().split("\\s");
            // 打ち込まれた入力が1文字だった場合（例： pass , 5  etc...）
            if(s.length==1){
                // 「 pass 」と打ち込まれた場合
                if(s[0].equals("pass")){
                    ret = 100 ;
                    break;
                }
                // 「 exit 」と打ち込まれた場合
                else if(s[0].equals("exit")){
                    ret = 200 ;
                    break;
                }
            }
            // 打ち込まれた入力が2文字だった場合（例： 7 2 , give up  etc...）
            else if(s.length==2){
                // 「 s t 」と打ち込まれた場合（s,tはそれぞれ1から8までの数字とする）
                if(s[0].matches(RANGE) && s[1].matches(RANGE)){
                    y = Integer.parseInt(s[0]);
                    x = Integer.parseInt(s[1]);
                    break;
                }
            }
            // 例外だった場合に表示
            System.out.println("駒を置くことができませんでしたので、もう一度入力してください。");
            System.out.print(">>>>> ");

        }
        return ret;
    }

}

