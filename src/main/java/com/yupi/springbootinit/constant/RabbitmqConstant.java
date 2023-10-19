package com.yupi.springbootinit.constant;

public interface RabbitmqConstant {


    /* 短信相关 */
    String QUEUE_LOGIN_SMS = "queue_sms_code";
    String EXCHANGE_SMS_INFORM ="exchange_sms_inform";
    String ROUTINGKEY_SMS ="inform.login.sms";


    /*BI相关*/
    String BI_QUEUE_NAME = "bi_queue_name";
    String BI_QUEUE_DEAD_NAME = "bi_queue_dead_name";
    String BI_EXCHANGE_NAME ="bi_exchange_name";
    String BI_ROUTING_KEY ="bi_routing_key";
    String BI_EXCHANGE_DEAD = "bi_exchange_dead_name";


}