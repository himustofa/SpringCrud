package com.mk.SpringCrud.utils;

public class Utility {



    //https://stackoverflow.com/questions/57477731/saving-image-to-server-directory-using-springboot-with-android
    //https://androidclarified.com/android-image-upload-example/
    //https://www.youtube.com/watch?v=Hef5pJkNCvA
    //https://medium.com/tekraze/fetch-image-from-url-in-spring-boot-java-to-upload-save-locally-6b90c2bdc2ba
    /*private void uploadFile() {
        Url imageUrl = new Url("https://site.com/image.jpeg");  // Sample url, replace with yours

        String destinationFile = "sample.jpg";

        *//*******************Multipart Upload Method*********************************
         **              To resources like minio or DB
         ***************************************************************************//*

        *//********
         * Step 1
         * Create Buffered Image by Reading from Url using ImageIO library
         ********//*
        BufferedImage image = ImageIO.read(imageUrl);

        *//********
         * Step 2
         * Create ByteArrayOutputStream object to handle Image data
         ********//*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        *//********
         * Step 3
         * Write as Image with Jpeg extension to byteArrayOutputStream created in previous step
         ********//*
        ImageIO.write(image,"jpg",byteArrayOutputStream);

        *//********
         * Step 4
         * Flush image created to byteArrayOutoputStream
         ********//*
        byteArrayOutputStream.flush();

        *//********
         * Step 5
         * Create Random file name but unique by adding timestamp with extension
         ********//*
        String fileName = RandomString.make() + new Date().getTime() + ".jpg";

        *//********
         * Step 6
         * Now Create MultipartFile using MockMultipartFile by providing
         * @param fileName name of the file
         * @param imageType like "image/jpg"
         * @param ByteArray from ByteArrayOutputStream
         ********//*
        MultipartFile multipartFile = new MockMultipartFile(fileName,fileName,imageType,byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.close() // Close once it is done saving

        *//********
         * Step 7
         * Now call Upload/Save method as you want
         ********//*

        fileUpload(multipartFile);   // call your upload/save method here
        *//***********file will be uploaded now*****************//*

        *//*****************File save Method*********************
         ***            To Some file like sample.jpg         ***
         ******************************************************//*

        *//********
         * Step 1
         * Create Input Stream from Url to store fetched file as stream temporarily
         ********//*
        InputStream inputStream = imageUrl.openStream();

        *//********
         * Step 2
         * Create Output Stream to write Imput Stream Data to a file locally
         ********//*
        OutputStream outputStream = new FileOutputStream(destinationFile);

        *//********
         * Step 3
         * Create Helper Variables for handling the Write process
         ********//*
        byte[] byteArray = new byte[2048];   // A byte array for checking the end of data stream
        int length;   // length for data stream

        *//********
         * Step 4
         * Set While loop to write data from Input Stream to file using Output Stream until the end of stream is finished and exit
         ********//*
        while ((length = inputStream.read(byteArray)) != -1) {
            outputStream.write(byteArray, 0, length);   // Will write data to file byte by byte of size 2048
        }

        *//********
         * Step 5
         * Close both Output Stream and Input Stream Connections
         ********//*
        inputStream.close();
        outputStream.close();
        *//************ File is saved now***********************//*


    }*/
}
