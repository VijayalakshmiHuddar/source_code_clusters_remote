 Volume in drive C has no label.
 Volume Serial Number is D8A5-E423

 Directory of C:\Users\Desktop\Data_archive_clusters

11-04-2019  19:35    <DIR>          .
11-04-2019  19:35    <DIR>          ..
11-04-2019  19:18             1,021 archive.sh
11-04-2019  19:19             1,125 archivebash.sh
11-04-2019  19:19             1,179 archivebourne.sh
11-04-2019  19:18             1,073 archive_one.sh
11-04-2019  19:35                 0 fusion.java
11-04-2019  19:06               359 java.txt
11-04-2019  19:31             1,339 javaarchive.java
11-04-2019  19:06               412 javaarchive.txt
11-04-2019  19:08               592 javaarchivecluster.txt
11-04-2019  19:08                 0 javaarchive_committed.txt
11-04-2019  19:31             1,397 javaarchive_one.java
11-04-2019  19:07               469 javaarchive_one.txt
11-04-2019  19:31             1,448 jcluster.java


oldfile=$1
newfile=$2

month=`date +%B`
year=`date +%Y`

prefix="frozenskys"

archivefile=$prefix.$month.$year.tar

# Check for existence of a compressed archive matching the naming convention
if [ -e $archivefile.gz ]
then
    echo "Archive file $archivefile already exists..."
    echo "Adding file '$oldfile' to existing tar archive..."

    # Uncompress the archive, because you can't add a file to a
    # compressed archive
    gunzip $archivefile.gz

    # Add the file to the archive
    tar --append --file=$archivefile $oldfile

    # Recompress the archive
    gzip $archivefile

# No existing archive - create a new one and add the file
else
    echo "Creating new archive file '$archivefile'..."
    tar --create --file=$archivefile $oldfile
    gzip $archivefile
fi

# Update the files outside the archive
mv $newfile $oldfile
11-04-2019  19:09               643 meanstack.txt
11-04-2019  19:30             1,228 mongodb.txt
11-04-2019  19:09    <DIR>          mongodb_cluster
11-04-2019  19:31             1,285 nuclear_fusion.java
11-04-2019  19:18               973 pythonarch.py
11-04-2019  19:15               864 pythoncluster.py
11-04-2019  19:10               751 pythoncluster.txt
11-04-2019  19:17               922 pythondataarchive.py
11-04-2019  19:15               810 pythondataarchive.txt
              21 File(s)         17,890 bytes
               3 Dir(s)   6,076,583,936 bytes free
package org.mano.example;

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

