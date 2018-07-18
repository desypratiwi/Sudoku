
import javafx.util.Pair;

public class Progress {

    // Pembuatan Soal 
    // yoyon
    static int[][] kotak = {{1, 0, 0, 0},
    {0, 2, 0, 0},
    {0, 0, 3, 0},
    {0, 0, 0, 4}
    };

    //Mencari yang kosong 
    // desy
    static boolean ada_kosong() {

        boolean ada = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kotak[i][j] == 0) {
                    ada = true;
                    return ada;
                }
            }
        }
        return ada;
    }

    // Cari nilai  x & y (x = baris) (y = kosong)/cari posisi yang kosong
    // desy
    static Pair<Integer, Integer> cari_posisi_kosong() {
        int x, y;
        Pair<Integer, Integer> posisi = new Pair(0, 0);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kotak[i][j] == 0) {
                    x = i;
                    y = j;
                    posisi = new Pair(i, j);
                    return posisi;
                }
            }
        }
        return posisi;
    }

    // Cek nilai di bagian baris
    // Nadya
    static boolean nilai_baris(int x, int y, int nilai) {

        boolean memenuhi = true;
        for (int i = 0; i < 4; i++) {
            if (i == y) {
                continue;
            } else if (kotak[x][i] == nilai) {
                memenuhi = false;
                return memenuhi;
            }
        }
        return memenuhi;
    }

    // Cek nilai di bagian kolom
    // yoyon
    static boolean nilai_kolom(int x, int y, int nilai) {

        boolean memenuhi = true;
        for (int i = 0; i < 4; i++) {
            if (i == x) {
                continue;
            } else if (kotak[i][y] == nilai) {
                memenuhi = false;
                return memenuhi;
            }
        }
        return memenuhi;
    }

    // Cari posisi kotak
    // nadya
    static int cari_kotak(int x, int y) {
        int kot = 0;
        if ((x >= 0 && x <= 1) && (y >= 0 && y <= 1)) {
            kot = 1;
        } else if ((x >= 0 && x <= 1) && (y >= 2 && y <= 3)) {
            kot = 2;
        } else if ((x >= 2 && x <= 3) && (y >= 0 && y <= 1)) {
            kot = 3;
        } else if ((x >= 2 && x <= 3) && (y >= 2 && y <= 3)) {
            kot = 4;
        }

        return kot;
    }

    // Cek nilai di bagian kotak
    // desy
    static boolean nilai_kotak(int x, int y, int posisi, int nilai) {
        boolean memenuhi = true;
        switch (posisi) {
            case 1: {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (i == x && j == y) {
                            continue;
                        } else if (kotak[i][j] == nilai) {
                            memenuhi = false;
                            return memenuhi;
                        }
                    }
                }
                break;
            }
            case 2: {
                for (int i = 0; i < 2; i++) {
                    for (int j = 2; j < 4; j++) {
                        if (i == x && j == y) {
                            continue;
                        } else if (kotak[i][j] == nilai) {
                            memenuhi = false;
                            return memenuhi;
                        }
                    }
                }
                break;
            }
            case 3: {
                for (int i = 2; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (i == x && j == y) {
                            continue;
                        } else if (kotak[i][j] == nilai) {
                            memenuhi = false;
                            return memenuhi;
                        }
                    }
                }
                break;
            }
            case 4: {
                for (int i = 2; i < 4; i++) {
                    for (int j = 2; j < 4; j++) {
                        if (i == x && j == y) {
                            continue;
                        } else if (kotak[i][j] == nilai) {
                            memenuhi = false;
                            return memenuhi;
                        }
                    }
                }
                break;
            }
        }
        return memenuhi;
    }

    // Menyelesaikan SUDOKU
    static boolean belum_selesai = true;

    static void selesaikan(int x, int y) {
        int i;
        for (i = 1; i < 5; i++) {
            int posisi = cari_kotak(x, y);
            boolean memenuhi = (nilai_baris(x, y, i)) && (nilai_kolom(x, y, i)) && (nilai_kotak(x, y, posisi, i));
            if ((memenuhi == true) && (belum_selesai == true)) {
                kotak[x][y] = i;
                if (ada_kosong() == true) {
                    Pair<Integer, Integer> kosong_selanjutnya = cari_posisi_kosong();
                    int x_baru = kosong_selanjutnya.getKey();
                    int y_baru = kosong_selanjutnya.getValue();
                    selesaikan(x_baru, y_baru);
                } else {
                    belum_selesai = false;
                }
            }
        }

    }

    static void cetak() {
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                System.out.print(kotak[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String Args[]) {
        //System.out.println(ada_kosong());
        //System.out.println(cari_posisi_kosong().getKey() + " "+ cari_posisi_kosong().getValue());
        cetak();
        Pair<Integer, Integer> kosong = cari_posisi_kosong();
        int x = kosong.getKey();
        int y = kosong.getValue();
        selesaikan(x, y);
        System.out.println("JAWABANNYAA ADALAAH.....");
        cetak();

    }
}
