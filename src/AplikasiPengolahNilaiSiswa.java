import java.io.*;
import java.util.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class AplikasiPengolahNilaiSiswa {
    public static void main(String[] args) {
        firstLaunch();
    }

    public static void header() {
        System.out.println("------------------------------------");
        System.out.println("Aplikasi Pengolahan Nilai Siswa");
        System.out.println("------------------------------------");
    }

    public static void description(String description) {
        System.out.println(description);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void firstLaunch() {
        try {
            Scanner dataInput = new Scanner(new File("/Users/idstar/Documents/Idstar/Java/Data and Output/data_sekolah.csv"));
            List<Integer> listNilaiSiswa = new ArrayList<>();

            dataInput.useDelimiter("[;\r\n]");
            while (dataInput.hasNext()) {
                if (dataInput.hasNextInt()) {
                    listNilaiSiswa.add(dataInput.nextInt());
                } else {
                    dataInput.next();
                }
            }
            mainMenu(listNilaiSiswa);
        } catch (FileNotFoundException e) {
            reusableInput("File Tidak Ditemukan");
        }
    }

    public static void createModusFile(List<Integer> listNilai) {
        List<Integer> listKurangDari6 = listNilai.stream().filter(list -> list < 6).toList();
        List<Integer> list6 = listNilai.stream().filter(list -> list == 6).toList();
        List<Integer> list7 = listNilai.stream().filter(list -> list == 7).toList();
        List<Integer> list8 = listNilai.stream().filter(list -> list == 8).toList();
        List<Integer> list9 = listNilai.stream().filter(list -> list == 9).toList();
        List<Integer> list10 = listNilai.stream().filter(list -> list == 10).toList();
        try {
            File modusText = new File("/Users/idstar/Documents/Idstar/Java/Data and Output/data_sekolah_modus.txt");
            if (modusText.createNewFile()) {
                try {
                    FileWriter myWriter = getFileWriterModus(listKurangDari6, list6, list7, list8 ,list9,list10);
                    myWriter.close();
                    reusableInput("File telah di generate di /Users/idstar/Documents/Idstar/Java/Data and Output\nMohon dicek");
                } catch (IOException e) {
                    System.out.println("Ada kesalahan saat input data ke file");
                }
            } else {
                reusableInput("Nama file sudah ada");
            }
        } catch (IOException e) {
            reusableInput("Ada kesalahan saat membuat file");
        }
    }

    private static FileWriter getFileWriterModus(List<Integer> listKurang6, List<Integer> list6, List<Integer> list7, List<Integer> list8, List<Integer> list9, List<Integer> list10) throws IOException {
        FileWriter myWriter = new FileWriter("/Users/idstar/Documents/Idstar/Java/Data and Output/data_sekolah_modus.txt");
        myWriter.write("Berikut Hasil Pengolahan Nilai: \n");
        myWriter.write("Nilai\t\t\t|\tFrekuensi\n");
        myWriter.write("Kurang dari 6\t\t|\t" + listKurang6.size() + "\n");
        myWriter.write("6\t\t\t|\t" + list6.size() + "\n");
        myWriter.write("7\t\t\t|\t" + list7.size() + "\n");
        myWriter.write("8\t\t\t|\t" + list8.size() + "\n");
        myWriter.write("9\t\t\t|\t" + list9.size() + "\n");
        myWriter.write("10\t\t\t|\t" + list10.size()+ "\n");
        return myWriter;
    }

    public static double getMean(List<Integer> listNilai) {
        double mean = 0;
        for (Integer integer : listNilai) mean += integer;
        mean /= listNilai.size();

        return mean;
    }

    public static double getMedian(List<Integer> listNilai) {
        Collections.sort(listNilai);
        double median;
        int n = listNilai.size();

        if (n % 2 == 1) {
            median = listNilai.get(n / 2);
        } else {
            median = (listNilai.get(n / 2) + listNilai.get(n / 2 - 1)) / 2.0;
        }

        return median;
    }

    public static int getModus(List<Integer> listNilai)
    {
        HashMap<Integer,Integer> hashMapNilai = new HashMap<>();
        int max  = 1;
        int temp = 0;

        for (Integer integer : listNilai) {

            if (hashMapNilai.get(integer) != null) {

                int count = hashMapNilai.get(integer);
                count++;
                hashMapNilai.put(integer, count);

                if (count > max) {
                    max = count;
                    temp = integer;
                }
            } else
                hashMapNilai.put(integer, 1);
        }
        return temp;
    }

    public static void createModusMedianFile(List<Integer> listNilai) {
        Double mean = getMean(listNilai);
        Double median = getMedian(listNilai);
        int modus = getModus(listNilai);
        try {
            File modusText = new File("/Users/idstar/Documents/Idstar/Java/Data and Output/data_sekolah_modus_median.txt");
            if (modusText.createNewFile()) {
                try {
                    FileWriter myWriter = getFileWriterModusMedian(mean, median, modus);
                    myWriter.close();
                    reusableInput("File telah di generate di /Users/idstar/Documents/Idstar/Java/Data and Output\nMohon dicek");
                } catch (IOException e) {
                    System.out.println("Ada kesalahan saat input data ke file");
                }
            } else {
                reusableInput("Nama file sudah ada");
            }
        } catch (IOException e) {
            reusableInput("Ada kesalahan saat membuat file");
        }
    }

    private static FileWriter getFileWriterModusMedian(Double mean, Double median, int modus) throws IOException {
        FileWriter myWriter = new FileWriter("/Users/idstar/Documents/Idstar/Java/Data and Output/data_sekolah_modus_median.txt");
        myWriter.write("Berikut Hasil Pengolahan Nilai: \n");
        myWriter.write("Berikut hasil sebaran data nilai\n");
        myWriter.write("Mean: " + mean + "\n");
        myWriter.write("Median: " + median + "\n");
        myWriter.write("Modus: " + modus + "\n");
        return myWriter;
    }

    public static void createTwoFile(List<Integer> listNilai) {
        List<Integer> listKurangDari6 = listNilai.stream().filter(list -> list < 6).toList();
        List<Integer> list6 = listNilai.stream().filter(list -> list == 6).toList();
        List<Integer> list7 = listNilai.stream().filter(list -> list == 7).toList();
        List<Integer> list8 = listNilai.stream().filter(list -> list == 8).toList();
        List<Integer> list9 = listNilai.stream().filter(list -> list == 9).toList();
        List<Integer> list10 = listNilai.stream().filter(list -> list == 10).toList();
        Double mean = getMean(listNilai);
        Double median = getMedian(listNilai);
        int modus = getModus(listNilai);
        try {
            File modusText = new File("/Users/idstar/Documents/Idstar/Java/Data and Output/data_sekolah_modus_median.txt");
            if (modusText.createNewFile()) {
                try {
                    FileWriter fileWriter = getFileWriterModus(listKurangDari6, list6, list7, list8 ,list9,list10);
                    FileWriter myWriter = getFileWriterModusMedian(mean, median, modus);
                    fileWriter.close();
                    myWriter.close();
                    reusableInput("File telah di generate di /Users/idstar/Documents/Idstar/Java/Data and Output\nMohon dicek");
                } catch (IOException e) {
                    System.out.println("Ada kesalahan saat input data ke file");
                }
            } else {
                reusableInput("Nama file sudah ada");
            }
        } catch (IOException e) {
            reusableInput("Ada kesalahan saat membuat file");
        }
    }

    public static void mainMenu(List<Integer> listNilai) {
        Scanner sc = new Scanner(System.in);

        header();
        System.out.println();
        description("Letakkan file csv dengan nama file data_sekolah di direktori " +
                "berikut: /Users/idstar/Documents/Idstar/Java/Data and Output");
        System.out.println();
        System.out.println("Pilih Menu: ");
        System.out.println("1. Generate txt untuk menampilkan modus");
        System.out.println("2. Generate txt untuk menampilkan nilai rata-rata, median");
        System.out.println("3. Generate kedua file");
        System.out.println("0. Exit");
        int input = sc.nextInt();

        switch (input) {
            case 0:
                exit();
                break;
            case 1:
                createModusFile(listNilai);
                break;
            case 2:
                createModusMedianFile(listNilai);
                break;
            case 3:
                createTwoFile(listNilai);
                break;
            default:
                reusableInput("Input tidak sesuai, mohon input sesuai data yang tersedia");
                break;
        }
    }

    public static void reusableInput(String description) {
        Scanner sc = new Scanner(System.in);

        header();
        System.out.println();
        description(description);
        System.out.println();
        System.out.println("Pilih Menu: ");
        System.out.println("0. Exit");
        System.out.println("1. Kembali ke menu awal");
        int input = sc.nextInt();

        if (input == 1) {
            firstLaunch();
        } else {
            exit();
        }
    }
}