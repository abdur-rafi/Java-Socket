public static void sendFile(File file, Socket socket, ObjectOutputStream oos){
// file -> The file that is to be sent
// socket -> socket which will be used
// oos -> pass the oos if you already have created one, else pass null
        byte[] buffer = new byte[100000];
        try {
            if(oos == null){
                oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            }
            System.out.println("   ==================== Sending File ======================");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            System.out.println("   File Info : \n"
                    + "   name        : " + file.getName() + "\n"
                    + "   size        : " + file.length() + " bytes"
            );
            oos.writeUTF(file.getName());
            oos.flush();
            oos.writeLong(file.length());
            oos.flush();
            double sz = file.length();
            double s = 0L;
            int count;
            while ((count = bis.read(buffer)) > 0) {
                oos.write(buffer, 0, count);
            }
            System.out.println("=================== Finished Sending File ====================");
            oos.flush();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
