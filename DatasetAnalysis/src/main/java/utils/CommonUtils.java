package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains basic utility functions
 * @author Manos Chatzakis (chatzakis@ics.forth.gr)
 * @author Eva Chamilaki (evacham7@gmail.com)
 */
public class CommonUtils {
     public static int[] generateRandomNums(int count, int min, int max) {
        ArrayList<Integer> randomNums = new ArrayList<>();
        int randomNumsCount = 0;

        while (randomNumsCount < count) {
            int rand = generateRandomNum(min, max);
            if (!randomNums.contains(rand)) {
                randomNums.add(rand);
                randomNumsCount++;
            }
        }
        int[] res = randomNums.stream().mapToInt(i -> i).toArray();
        return res;
    }

    public static Integer[] ArrayList2Array(ArrayList<Integer> list) {
        return (Integer[]) list.toArray();
    }

    public static int generateRandomNum(int min, int max) {
        //System.out.println("Random value in int from " + min + " to " + max + ":");
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
        //System.out.println(random_int);
    }

    public static String writeStringToFile(String data, String filepath) {
        File file = new File(filepath);

        try (FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] bytes = data.getBytes();
            bos.write(bytes);
            bos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }
}
