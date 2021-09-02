package lichens.licht.arglasses;

import androidx.appcompat.app.AppCompatActivity;
import lichens.licht.arglasses.utils.WifiAdmin;
import lichens.licht.arglasses.utils.WifiData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ConnectStateActivity extends AppCompatActivity {

    private static final String TAG = "ConnectStateActivity";
    private WifiAdmin wifiAdmin;
    private int netType;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_state);
        wifiAdmin = new WifiAdmin(this);

        gson = new Gson();

        if (!wifiAdmin.mWifiManager.isWifiEnabled()) {
            wifiAdmin.openWifi();
        }
        findViewById(R.id.tv_switch_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(ConnectStateActivity.this)
                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)// 扫码的类型,可选：一维码，二维码，一/二维码
                        .setPrompt("请对准二维码")// 设置提示语
                        .setCameraId(0)// 选择摄像头,可使用前置或者后置
                        .setBeepEnabled(true)// 是否开启声音,扫完码之后会"哔"的一声
                        .initiateScan();// 初始化扫码
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫码结果
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                //扫码失败
            } else {
                String result = intentResult.getContents();//返回值
                Log.e(TAG, "onActivityResult: " + result );

                WifiData wifiData = gson.fromJson("{" + result + "}", WifiData.class);

                Log.i(TAG, "netWorkName: " + wifiData.qrcode.ssid + " netWorkType: " + wifiData.qrcode.ap + " password: " + wifiData.qrcode.pwd);
                netType = WifiAdmin.TYPE_WPA;
                wifiAdmin.addNetwork(wifiData.qrcode.ssid, wifiData.qrcode.pwd, netType);
            }
        }
    }
}