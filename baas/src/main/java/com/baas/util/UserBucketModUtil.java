package com.baas.util;

import org.apache.commons.lang.math.NumberUtils;


public class UserBucketModUtil {

    /**
     *
     * 
     * @param userId
     * @param bucketCount
     * @return
     */
    public static int getUserBucketMod(String userId, int bucketCount) {
        if (StringUtil.isBlank(userId)) {
            return 0;
        }

        int bukLen = String.valueOf(bucketCount).length();
        int uidLen = userId.length();

        String uid = (uidLen < bukLen) ? userId : userId.substring(uidLen - bukLen, uidLen - 1);
        int mod = (int) (NumberUtils.toLong(uid, 0) % bucketCount);

        return mod;
    }

}