package com.shop.alipay.demo;

public class AlipassSDKConstants {

    /**
     * 商户在开放平台的应用的app_id，请确认应用有卡券包功能
     */
    public static final String APP_ID = "2016032301235296";

    /**合作伙伴ID*/
    public static final String PID = "2088802441917053";

    /**
     * 商户私钥 上述应用公钥对应的私钥
     */
    public static final String PRIVATE_KEY = "MIICXgIBAAKBgQDSOihyt8L/y2pXJIlCoDhugK3ry5/qjG2wtFH4VmAF2sBxuLXH\n"
            + "LK0YM4hCLq5iUzY5lwT6NvOCtPSYUWFggwMZ9tdeCmO+c61pyBsNB71Q1Hj/aWSV\n"
            + "OvPzXnpdDOQSA2SPMJV9KCF2wGY21/SNAiC9B3d4HK4pJeTeNpDM1hB2mQIDAQAB\n"
            + "AoGAfoYYH+rvBmpTpvzLS3kIXaE+i4JyzYQU5FuyVccOZZehaRAk/ympR76EDGjx\n"
            + "9SVlpkV47mqyJgFokF+yPpa6/waq185vU2UXlyCRh4BddYhMf/oNgJkAtv5DjqWV\n"
            + "0kS6TbCYv31pX18BbhFAdTsKUD2865xK+zlz7L34Fq83XIECQQD2bpos/fC5/z7l\n"
            + "TMB5zIkKYBgsRjf5J6HC1HHHiL9ydXBqDPCXdH7HPr9QJ8BFkH4PbnnNxh3wLRhy\n"
            + "TUd8BCOpAkEA2mOzFDOaBS7Z0EjX6qLqgGYZGcnSqH/1ogtHpBz7v03Z39LUtg38\n"
            + "/OTXu6D16i/yRzrMXQaQju8CyIxqpfqRcQJBAKnG00NSBGowFsXlFeoT5woV4DGI\n"
            + "gwGNs11F/clpRfR2Qmdd2NePc9STPzza1hVXEfJRu2rwJioU93WwFiKzu1kCQQDU\n"
            + "XVj5zwf5/EogiYU1DIF+pEnKUwC/i9bfYZqj0u9XYyv3w/vGoSme/iVutiIMPd2a\n"
            + "OGocF9Mguvj47dqTVmpxAkEAh9+pZnKEAafy4/VV/EeJv8kwTi9pntR4dBfaWmNl\n" + "TBOU/sKPKiXg4QZ88Jo3Vk5EI+mMQXfGzlG0+gYdqSDvcA==";
    /**
     * 支付宝公钥 在上述应用中查看
     */
    public static final String ALIPAY_PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    /**
     * 支付宝网关
     */
    public static final String ALIPAY_GATEWAY = "https://openapi.alipay.com/gateway.do";

    /**
     * 字符编码-传递给支付宝的数据编码
     */
    public static final String CHARSET = "utf-8";

    public static String FORMAT = "json";

}
