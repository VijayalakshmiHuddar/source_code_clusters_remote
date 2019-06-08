import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveDemo2 {

   private static ZipOutputStream zout;

   public static void main(String[] args)
      throws IOException, FileNotFoundException {

         String zipFile = "myzipfile.zip";
         String[] files = { "myfile.txt, /home/mano/doc/file2.txt,
             file3.png" };
         zip(zipFile, files);
      }

      public static void zip(String zipFile, String[] files)
            throws IOException, FileNotFoundException {
         String currentDirectory = System.getProperty("user.dir");

      zout = new ZipOutputStream(new
         BufferedOutputStream(new FileOutputStream(zipFile)));
      zout.setLevel(Deflater.BEST_COMPRESSION);
      for (int i = 0; i < files.length; i++) {
      File file = new File(files[i]);
      if (!file.exists()) {
         System.out.println("File " + file.getAbsolutePath()
            + " not found ");
         System.out.println("Aborted.");
            return;
      }
      ZipEntry ze = new ZipEntry(files[i]);
      zout.putNextEntry(ze);

      BufferedInputStream buffin = new BufferedInputStream(new
         FileInputStream(files[i]));

      byte[] buffer = new byte[1024];
      int count = -1;
      while ((count = buffin.read(buffer)) != -1) {
         zout.write(buffer, 0, count);
      }
         buffin.close();
      }

      zout.closeEntry();
      zout.close();
      System.out.println("Output written to "
         + currentDirectory + File.separator + zipFile);
   }
}
To read the content of a ZIP-formatted file:
package org.mano.example;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveDemo3 {

   public static void main(String[] args) {
      String zipFile = "myzipfile.zip";
      String unziploc = "/home/mano/test";
      unzip(zipFile, unziploc);
   }

   public static void unzip(String zipFile, String unziploc) {
      try (ZipInputStream zin = new ZipInputStream(new
         BufferedInputStream(new FileInputStream(zipFile)))) {

      ZipEntry ze = null;
      while ((ze = zin.getNextEntry()) != null) {

      File file = new File(unziploc + File.separator
         + ze.getName());
      File root = file.getParentFile();
      if (!root.exists()) root.mkdirs();
      file.createNewFile();
      BufferedOutputStream buffout = new BufferedOutputStream(
      new FileOutputStream(unziploc + File.separator
         + ze.getName()));
      byte[] buffer = new byte[1024];
      int count = -1;
      while ((count = zin.read(buffer)) != -1) {
         buffout.write(buffer, 0, count);
      }
         buffout.close();
      }

      System.out.println("Contents extracted to " + (new
         File(unziploc)).getAbsolutePath());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
