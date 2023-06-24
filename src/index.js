import { NativeModules } from 'react-native';

const module = NativeModules.UniqueDeviceIdModule;
let deviceId = null;

/**
 * Native Module responsible for fetching device's unique id.
 * It uses MediaDRM Class to access DEVICE_UNIQUE_ID property.
 */
const UniqueDeviceIdModule = {
    /**
     * call this method in-order to obtain the unique id of this device.
     * @return {string}
     */
    getDeviceId: () => {
        if (deviceId === null) {
            deviceId = module.getUniqueDeviceId();
        }
        return deviceId;
    },
};

export default UniqueDeviceIdModule;
