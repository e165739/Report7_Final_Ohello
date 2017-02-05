package jp.ac.uryukyu.ie.e165739;

public class Main {
    public static void main(String[] args) {

        Solver solver = new Solver();
        solver.init();

        System.out.println("----------------------------- オセロ -----------------------------");
        System.out.println("遊び方：5行4列目に入れたい（縦5、横4）。---> 「5 4」と入力する。");
        System.out.println("その他：パス（置く場所がない）: pass   　強制終了：exit\n");


        out:
        while (true) {
            // 盤面、ターンを常に表示させる
            solver.show();
            solver.black_white_turn();

            System.out.print("駒を入力>>>>> ");

            switch (solver.input()) {

                // PASSとEXITを今回は定数式で返すようにした

                // 「 pass 」と入力されたらターンを飛ばす
                case 100 : {
                    solver.shift();
                    continue;
                }
                // 「 exit 」と入力されたらゲームを強制終了させる
                case 200 : {
                    System.out.println("EXITされました。ゲームを終了します。");
                    break out; // 実装終了
                }
            }


            if(solver.update()) {
                if(solver.judge()) {
                    solver.show_result();
                    break;
                }
                solver.shift();
            }else{
                System.out.print("\n駒が置けません。再入力してください。\n\n ");
            }
        }
    }
}
