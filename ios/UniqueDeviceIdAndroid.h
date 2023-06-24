
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNUniqueDeviceIdAndroidSpec.h"

@interface UniqueDeviceIdAndroid : NSObject <NativeUniqueDeviceIdAndroidSpec>
#else
#import <React/RCTBridgeModule.h>

@interface UniqueDeviceIdAndroid : NSObject <RCTBridgeModule>
#endif

@end
