import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class MvFilesTo {
    public static String FilePath;
    public static String Param;
    public static String TargetPath;
    public static void main(String args[]){
        System.out.println("请输入要提取的文件夹，以回车结束：");
        Scanner scanner = new Scanner(System.in);
        FilePath = scanner.nextLine();

        System.out.println("请输入提取到的文件夹，以回车结束：");
        TargetPath = scanner.nextLine();

        System.out.println("请输入要提取的文件类型，以回车结束：");
        Param = scanner.nextLine();
        scanner.close();

        MvFilesTo mvFilesTo = new MvFilesTo();
        mvFilesTo.mvAllFiles(new File(FilePath));

    }
    //移动此文件夹下所有给定类型文件到指定文件夹
    public void mv(File file) {
        Arrays.stream(file.listFiles()).forEach(fileIn -> {
            String[] split = fileIn.getName().split("\\.");
            if (split[split.length - 1].equals(Param)){
                fileIn.renameTo(new File(TargetPath+"\\"+fileIn.getName()));
            }
        });
    }

    //递归移动所有目录
    public void mvAllFiles(File file) {
        if (file.isDirectory()) {
            mv(file);
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    mv(f);
                    mvAllFiles(f);
                }
            }
        }
    }
}
