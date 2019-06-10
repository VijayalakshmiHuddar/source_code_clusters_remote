import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ArchiveDemo {

   public static void main(String[] args)
         throws IOException, DataFormatException {

      String inputData = "An archive refers to a collection of one or more files put together as a single unit. Often in Java programs, we come across an archive file called a JAR (Java Archive). This type of file is common to every Java programmer. Archive files are created by using file archive software such as WinZip, 7-zip, tar, and so forth. These types of files are particularly useful to store and transmit multiple files as a single unit. File archives sometime employ data compression and encryption as well. This article delves into some of the key concepts of working with archive files by using Java programming."

      byte[] compressedByte = compress(inputData.getBytes(),
         Deflater.BEST_COMPRESSION, false);
      byte[] decompressedByte=decompress(compressedByte, false);

      String outputData=new String(decompressedByte);

      System.out.println("Input Data: " + inputData);
      System.out.println("Uncompressed data length: "
         + inputData.getBytes().length);
      System.out.println("Compressed data length: "
         + compressedByte.length);
      System.out.println("Decompressed data length: "
         + decompressedByte.length);
      System.out.println("Output Data: " + outputData);

   }

   public static byte[] decompress(byte[] input, boolean format)
         throws IOException, DataFormatException {
      Inflater inflater = new Inflater(format);
      inflater.setInput(input);
      ByteArrayOutputStream baout = new ByteArrayOutputStream();
      byte[] buff = new byte[1024];
      int count = 0;

      while (!inflater.finished()) {
         count = inflater.inflate(buff);
         if ( count > 0)
            baout.write(buff, 0, count);
      }
      inflater.end();
      return baout.toByteArray();
   }

   public static byte[] compress(byte[] data, int compressionLevel,
         boolean format) throws IOException {

      Deflater deflater = new Deflater(compressionLevel, format);
      deflater.setInput(data);
      deflater.finish();

      ByteArrayOutputStream baout = new ByteArrayOutputStream();
      byte[] buff = new byte[1024];
      int count = 0;

      while (!deflater.finished()) {
         count = deflater.deflate(buff);
         if (count > 0)
            baout.write(buff, 0, count);
      }
      deflater.end();
      return baout.toByteArray();
   }
}

