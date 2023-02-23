package com.example.imagedata.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {

    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 1024];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(temp, 0, deflater.deflate(temp));
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception ignored) {

        }

        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] temp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                byteArrayOutputStream.write(temp, 0, inflater.inflate(temp));
            }
            byteArrayOutputStream.close();
        } catch (Exception ignored) {

        }

        return byteArrayOutputStream.toByteArray();
    }

}
