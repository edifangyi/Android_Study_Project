
smsList = new ArrayList<>();


List<Sms> smsList;

    private void savesms() {
        //虚构10条短信
        for (int i = 0; i < 10; i++) {
            Sms sms = new Sms("138" +i+i, System.currentTimeMillis() + "", "1", "哈哈" + i);
            smsList.add(sms);
        }
    }

    public void clicke(View v) {
        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version=\"1.0\" encoding=\"utf-\"?1>");

        sb.append("<messages>");
        for (Sms sms : smsList) {
            sb.append("<message>");

            sb.append("<address>");
            sb.append(sms.getAddress());
            sb.append("</address>");

            sb.append("<date>");
            sb.append(sms.getDate());
            sb.append("</date>");

            sb.append("<type>");
            sb.append(sms.getType());
            sb.append("</type>");

            sb.append("<body>");
            sb.append(sms.getBody());
            sb.append("</body>");

            sb.append("</message>");
        }
        sb.append("</messages>");


        File file = new File("sdcard/sms.xml");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }