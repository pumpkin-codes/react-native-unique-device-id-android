package com.uniquedeviceidandroid;
import android.media.MediaDrm;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.UUID;

public class DeviceIdModule extends ReactContextBaseJavaModule {
    public static final String NAME = "UniqueDeviceIdModule";

    public DeviceIdModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return DeviceIdModule.NAME;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getUniqueDeviceId() {
        final long mostSigBits = -0x121074568629b532L;
        final long leastSigBits = -0x5c37d8232ae2de13L;
        UUID wideVineUuid = new UUID(mostSigBits, leastSigBits);
        MediaDrm wvDrm = null;
        String idStr = null;
        try {
            wvDrm = new MediaDrm(wideVineUuid);
            byte[] wideVineId = wvDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID);
            //convert the array in string.
            idStr = Base64.encodeToString(wideVineId, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // close the resource
        if (wvDrm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                wvDrm.close();
            } else {
                wvDrm.release();
            }
        }
        return idStr;
    }
}
