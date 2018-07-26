package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    //лічильники для того щоб дізнатися чи ранив чи вбив.
    static int[] $1_4 = {4};
    static int[] $1_3 = {3};
    static int[] $1_33 = {3};
    static int[] $2_4 = {4};
    static int[] $2_3 = {3};
    static int[] $2_33 = {3};

    //чи закінчена гра чи ні?
    static boolean grazakin4ena = false;
    //хто саме переміг(плюсуємо попадіння в ціль, коли є 20 попадінь - є переможець)
    static int x1 = 0;
    static int x2 = 0;

    public static void main(String[] args) {
        //ініціалізуємо поля (перше поле вручну для наглядності решту циклом)
        String[][] sitka1 = {
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"},
        };
        String[][] sitka2 = new String[12][12];
        for (int ryad = 0; ryad < sitka2.length; ryad++) {
            for (int stovp = 0; stovp < sitka2[ryad].length; stovp++) {
                sitka2[ryad][stovp] = "-";
            }
        }

        String[][] postril1 = new String[12][12];
        for (int ryad = 0; ryad < postril1.length; ryad++) {
            for (int stovp = 0; stovp < postril1[ryad].length; stovp++) {
                postril1[ryad][stovp] = "-";
            }
        }
        String[][] postril2 = new String[12][12];
        for (int ryad = 0; ryad < postril2.length; ryad++) {
            for (int stovp = 0; stovp < postril2[ryad].length; stovp++) {
                postril2[ryad][stovp] = "-";
            }
        }

        Scanner scan = new Scanner(System.in);
        int playerTurn[] = new int[2];
        int kilkistkorabliv = 0;
        int[] misce = new int[2];
        int[] typ = {4, 3, 33, 2, 2, 2, 1, 1, 1, 1};

        Rozstanovka(kilkistkorabliv, misce, typ, sitka1);
        Rozstanovka(kilkistkorabliv, misce, typ, sitka2);
        Rezultat(sitka1);
        System.out.println();
        Rezultat(sitka2);
        System.out.println();

        while (grazakin4ena != true) {
            boolean perehidhodu = false;
            while (perehidhodu == false) {
                System.out.println("ГРАВЕЦЬ 1 ВВЕДІТЬ КООРДИНАТИ ПОСТРІЛУ ЧЕРЕЗ ПРОБІЛ ВІД 1 ДО 10: ");
                playerTurn[0] = scan.nextInt();
                playerTurn[1] = scan.nextInt();

                if (sitka2[playerTurn[0]][playerTurn[1]].equals("-") || sitka2[playerTurn[0]][playerTurn[1]].equals("X")) {
                    System.out.println("ПРОМАХ АБО ВИ ВЖЕ ТУДИ СТРІЛЯЛИ. ЧЕРГА ІНШОГО ГРАВЦЯ");
                    postril1[playerTurn[0]][playerTurn[1]] = "*";
                    perehidhodu = true;
                } else {
                    if (sitka2[playerTurn[0]][playerTurn[1]].equals("4")) {
                        ranyvvbyvkorabel(sitka2, postril1, playerTurn[0], playerTurn[1], $1_4);
                    } else if (sitka2[playerTurn[0]][playerTurn[1]].equals("3")) {
                        ranyvvbyvkorabel(sitka2, postril1, playerTurn[0], playerTurn[1], $1_3);
                    } else if (sitka2[playerTurn[0]][playerTurn[1]].equals("33")) {
                        ranyvvbyvkorabel(sitka2, postril1, playerTurn[0], playerTurn[1], $1_33);
                    } else if (sitka2[playerTurn[0]][playerTurn[1]].equals("2")) {
                        ranyvvbyvkorabel(sitka2, postril1, playerTurn[0], playerTurn[1], "2");
                    } else {
                        System.out.println("ВБИВ");
                        sitka2[playerTurn[0]][playerTurn[1]] = "X";
                    }
                    perehidhodu = false;
                    System.out.println("ВАШЕ ПОЛЕ");
                    Rezultat(sitka1);
                    System.out.println();
                    System.out.println("ВАШІ ПОСТРІЛИ");
                    Rezultat(postril1);
                    System.out.println();
                }
            }
            peremoga1(sitka2, sitka1);
            if (grazakin4ena == true) {
                break;
            }

            perehidhodu = false;
            while (perehidhodu == false) {
                System.out.println("ГРАВЕЦЬ 2 ВВЕДІТЬ КООРДИНАТИ ПОСТРІЛУ ЧЕРЕЗ ПРОБІЛ ВІД 1 ДО 10: ");
                playerTurn[0] = scan.nextInt();
                playerTurn[1] = scan.nextInt();

                if (sitka1[playerTurn[0]][playerTurn[1]].equals("-") || sitka1[playerTurn[0]][playerTurn[1]].equals("X")) {
                    System.out.println("ПРОМАХ АБО ВИ ВЖЕ ТУДИ СТРІЛЯЛИ. ЧЕРГА ІНШОГО ГРАВЦЯ");
                    postril2[playerTurn[0]][playerTurn[1]] = "*";
                    perehidhodu = true;
                } else {
                    if (sitka1[playerTurn[0]][playerTurn[1]].equals("4")) {
                        ranyvvbyvkorabel(sitka1, postril2, playerTurn[0], playerTurn[1], $2_4);
                    } else if (sitka1[playerTurn[0]][playerTurn[1]].equals("3")) {
                        ranyvvbyvkorabel(sitka1, postril2, playerTurn[0], playerTurn[1], $2_3);
                    } else if (sitka1[playerTurn[0]][playerTurn[1]].equals("33")) {
                        ranyvvbyvkorabel(sitka1, postril2, playerTurn[0], playerTurn[1], $2_33);
                    } else if (sitka1[playerTurn[0]][playerTurn[1]].equals("2")) {
                        ranyvvbyvkorabel(sitka1, postril2, playerTurn[0], playerTurn[1], "2");
                    } else {
                        System.out.println("ВБИВ");
                        sitka1[playerTurn[0]][playerTurn[1]] = "X";
                    }
                    perehidhodu = false;
                    System.out.println("ВАШЕ ПОЛЕ");
                    Rezultat(sitka2);
                    System.out.println();
                    System.out.println("ВАШІ ПОСТРІЛИ");
                    Rezultat(postril2);
                    System.out.println();
                }
            }
            peremoga2(sitka1, sitka2);
            if (grazakin4ena == true) {
                break;
            }
        }
    }

    //розташування кораблів комп'ютером для обох гравців випадковим чином.
    static void Rozstanovka(int kilkistkorabliv, int[] misce, int[] typ, String sitka1[][]) {
        Random rnd = new Random();
        while (kilkistkorabliv < 10) {
            int gor = rnd.nextInt(2);
            int typkorabla = 4;
            for (int i = 0; i <= kilkistkorabliv; i++) {
                typkorabla = typ[i];
            }
            boolean emisce;
            do {
                emisce = false;

                if (typkorabla == 4 && gor == 1) {
                    misce[0] = 1 + rnd.nextInt(10);
                    misce[1] = 1 + rnd.nextInt(7);

                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0] - 1][misce[1] + 2].equals("-") && sitka1[misce[0] - 1][misce[1] + 3].equals("-") && sitka1[misce[0] - 1][misce[1] + 4].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] + 2].equals("-") && sitka1[misce[0]][misce[1] + 3].equals("-") && sitka1[misce[0]][misce[1] + 4].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] + 2].equals("-") && sitka1[misce[0] + 1][misce[1] + 3].equals("-") && sitka1[misce[0] + 1][misce[1] + 4].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < typkorabla; i++) {
                            sitka1[misce[0]][misce[1] + i] = String.valueOf(typkorabla);
                        }
                    } else {
                        emisce = false;
                    }

                } else if (typkorabla == 4 && gor == 0) {
                    misce[0] = 1 + rnd.nextInt(7);
                    misce[1] = 1 + rnd.nextInt(10);

                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 2][misce[1] - 1].equals("-") && sitka1[misce[0] + 3][misce[1] - 1].equals("-") && sitka1[misce[0] + 4][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 2][misce[1] + 1].equals("-") && sitka1[misce[0] + 3][misce[1] + 1].equals("-") && sitka1[misce[0] + 4][misce[1]].equals("-") && sitka1[misce[0] + 4][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 2][misce[1]].equals("-") && sitka1[misce[0] + 3][misce[1]].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < typkorabla; i++) {
                            sitka1[misce[0] + i][misce[1]] = String.valueOf(typkorabla);
                        }
                    } else {
                        emisce = false;
                    }
                } else if (typkorabla == 3 && gor == 1) {
                    misce[0] = 1 + rnd.nextInt(10);
                    misce[1] = 1 + rnd.nextInt(8);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0] - 1][misce[1] + 2].equals("-") && sitka1[misce[0] - 1][misce[1] + 3].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] + 2].equals("-") && sitka1[misce[0]][misce[1] + 3].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] + 2].equals("-") && sitka1[misce[0] + 1][misce[1] + 3].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < typkorabla; i++) {
                            sitka1[misce[0]][misce[1] + i] = String.valueOf(typkorabla);
                        }
                    } else {

                        emisce = false;
                    }
                } else if (typkorabla == 3 && gor == 0) {
                    misce[0] = 1 + rnd.nextInt(8);
                    misce[1] = 1 + rnd.nextInt(10);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 2][misce[1] - 1].equals("-") && sitka1[misce[0] + 3][misce[1] - 1].equals("-") && sitka1[misce[0] + 3][misce[1]].equals("-") && sitka1[misce[0] + 3][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 2][misce[1]].equals("-") && sitka1[misce[0] + 2][misce[1] + 1].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < typkorabla; i++) {
                            sitka1[misce[0] + i][misce[1]] = String.valueOf(typkorabla);
                        }
                    } else {

                        emisce = false;
                    }
                } else if (typkorabla == 33 && gor == 1) {
                    misce[0] = 1 + rnd.nextInt(10);
                    misce[1] = 1 + rnd.nextInt(8);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0] - 1][misce[1] + 2].equals("-") && sitka1[misce[0] - 1][misce[1] + 3].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] + 2].equals("-") && sitka1[misce[0]][misce[1] + 3].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] + 2].equals("-") && sitka1[misce[0] + 1][misce[1] + 3].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < 3; i++) {
                            sitka1[misce[0]][misce[1] + i] = String.valueOf(typkorabla);
                        }
                    } else {

                        emisce = false;
                    }
                } else if (typkorabla == 33 && gor == 0) {
                    misce[0] = 1 + rnd.nextInt(8);
                    misce[1] = 1 + rnd.nextInt(10);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 2][misce[1] - 1].equals("-") && sitka1[misce[0] + 3][misce[1] - 1].equals("-") && sitka1[misce[0] + 3][misce[1]].equals("-") && sitka1[misce[0] + 3][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 2][misce[1]].equals("-") && sitka1[misce[0] + 2][misce[1] + 1].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < 3; i++) {
                            sitka1[misce[0] + i][misce[1]] = String.valueOf(typkorabla);
                        }
                    } else {

                        emisce = false;
                    }
                } else if (typkorabla == 2 && gor == 1) {
                    misce[0] = 1 + rnd.nextInt(10);
                    misce[1] = 1 + rnd.nextInt(9);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0] - 1][misce[1] + 2].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] + 2].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] + 2].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < typkorabla; i++) {
                            sitka1[misce[0]][misce[1] + i] = String.valueOf(typkorabla);
                        }
                    } else {

                        emisce = false;
                    }
                } else if (typkorabla == 2 && gor == 0) {
                    misce[0] = 1 + rnd.nextInt(9);
                    misce[1] = 1 + rnd.nextInt(10);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-") && sitka1[misce[0] + 2][misce[1] - 1].equals("-") && sitka1[misce[0] + 2][misce[1]].equals("-") && sitka1[misce[0] + 2][misce[1] + 1].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель
                        for (int i = 0; i < typkorabla; i++) {
                            sitka1[misce[0] + i][misce[1]] = String.valueOf(typkorabla);
                        }
                    } else {

                        emisce = false;
                    }
                } else if (typkorabla == 1) {
                    misce[0] = 1 + rnd.nextInt(10);
                    misce[1] = 1 + rnd.nextInt(10);


                    if (sitka1[misce[0]][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] - 1].equals("-") && sitka1[misce[0] - 1][misce[1]].equals("-") && sitka1[misce[0] - 1][misce[1] + 1].equals("-") && sitka1[misce[0]][misce[1] - 1].equals("-") && sitka1[misce[0]][misce[1] + 1].equals("-") && sitka1[misce[0] + 1][misce[1] - 1].equals("-") && sitka1[misce[0] + 1][misce[1]].equals("-") && sitka1[misce[0] + 1][misce[1] + 1].equals("-")) {
                        emisce = true;
                        kilkistkorabliv++;
                        // ставимо на місце корабель

                        sitka1[misce[0]][misce[1]] = String.valueOf(typkorabla);

                    } else {

                        emisce = false;
                    }
                }

            } while (emisce == false);
        }
    }

    //перевірка чи ранив чи вбив
    static void ranyvvbyvkorabel(String sitka[][], String postril[][], int player11, int player12, int[] k) {
        if (k[0] > 1) {
            System.out.println("РАНИВ");
            sitka[player11][player12] = "X";
            postril[player11][player12] = "X";
            k[0]--;
        } else {
            System.out.println("ВБИВ");
            sitka[player11][player12] = "X";
            postril[player11][player12] = "X";
            k[0]--;
        }
    }

    static void ranyvvbyvkorabel(String sitka[][], String postril[][], int player11, int player12, String k) {
        if (sitka[player11 + 1][player12].equals(k) || sitka[player11 - 1][player12].equals(k) || sitka[player11][player12 + 1].equals(k) || sitka[player11][player12 - 1].equals(k)) {
            System.out.println("РАНИВ");
            sitka[player11][player12] = "X";
            postril[player11][player12] = "X";
        } else {
            System.out.println("ВБИВ");
            sitka[player11][player12] = "X";
            postril[player11][player12] = "X";
        }
    }

    //визначаємо чи переміг гравець 1 чи гравець 2
    static void peremoga1(String sitka2[][], String sitka1[][]) {
        for (int ryad = 0; ryad < sitka2.length; ryad++) // Виведення сітки з результатом на екран.
        {
            for (int stovp = 0; stovp < sitka2[ryad].length; stovp++) {
                if (sitka2[ryad][stovp].equals("X")) {
                    x1++;
                }
            }
        }
        if (x1 == 20) {
            grazakin4ena = true;
            System.out.println("ВИГРАВ ГРАВЕЦЬ 1");
            Rezultat(sitka1);
            System.out.println();
            Rezultat(sitka2);
            System.exit(0);
        } else grazakin4ena = false;
    }

    static void peremoga2(String sitka1[][], String sitka2[][]) {
        for (int ryad = 0; ryad < sitka1.length; ryad++) // Виведення сітки з результатом на екран.
        {
            for (int stovp = 0; stovp < sitka1[ryad].length; stovp++) {
                if (sitka1[ryad][stovp].equals("X")) {
                    x2++;
                }
            }
        }
        if (x2 == 20) {
            grazakin4ena = true;
            System.out.println("ВИГРАВ ГРАВЕЦЬ 2");
            Rezultat(sitka1);
            System.out.println();
            Rezultat(sitka2);
            System.exit(0);
        } else grazakin4ena = false;
    }

    //виводимо результат бою
    public static void Rezultat(String masyv[][]) {
        for (int ryad = 0; ryad < masyv.length; ryad++) // Виведення сітки з результатом на екран.
        {
            System.out.println();
            for (int stovp = 0; stovp < masyv[ryad].length; stovp++) {
                System.out.print(masyv[ryad][stovp] + "\t");
            }
        }
    }
}