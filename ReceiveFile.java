public static void receiveFile(Socket socket,String filePath, String fileName,ObjectInputStream ois) {
// socket -> self-explanatory
// filePath -> where the received file will be stored. No need to put / at end
// fileName -> give a name if you want to save the file in different name. If you want to keep the actual name, pass null
// ois -> pass ois if you already opened one


        try {
            if(ois == null){
                ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            }
            System.out.println("   ==================== Receiving File ======================");
            int count = 0;
            String name = ois.readUTF();
            if (fileName != null) name = fileName;
            Long size = ois.readLong();
            long size2 = size;

            byte[] buffer = new byte[100000];
            FileOutputStream fos = new FileOutputStream(filePath+ "/" + name);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            while (size > 0 && (count = ois.read(buffer, 0, (int) Math.min(buffer.length, size))) > 0) {
                bos.write(buffer, 0, count);
                size -= count;
            }

            System.out.println("   File Info : \n"
                    + "   name        : " + name + "\n"
                    + "   size        : " + size2 + " bytes"
            );

            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

