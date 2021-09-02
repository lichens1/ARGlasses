package lichens.licht.arglasses.utils;

import java.io.Serializable;

public class WifiData implements Serializable {

    /**
     * qrcode : {"ssid":"huohe","pwd":"13829985487","ip":"192.168.1.9","ap":0,"ble_ssid":"Tenda"}
     */

    public QrcodeBean qrcode;

    public static class QrcodeBean implements Serializable {
        /**
         * ssid : huohe
         * pwd : 13829985487
         * ip : 192.168.1.9
         * ap : 0
         * ble_ssid : Tenda
         */

        public String ssid;
        public String pwd;
        public String ip;
        public int ap;
        public String ble_ssid;
    }
}
