package com.zhao.base.utils.eventbus

import org.greenrobot.eventbus.EventBus

/**
 * @Params
 * @Author ChenMo
 * @Date 2018.5.25
 */
object EventBusUtil {

    /**
     * 注册 EventBus
     *
     * @param subscriber
     */
    fun register(subscriber: Any) {
        val eventBus = EventBus.getDefault()
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber)
        }
    }

    /**
     * 解除注册 EventBus
     *
     * @param subscriber
     */
    fun unregister(subscriber: Any) {
        val eventBus = EventBus.getDefault()
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber)
        }
    }

    /**
     * 发送事件消息
     *
     * @param event
     */
    fun post(event: EventMessage<*>) {
        EventBus.getDefault().post(event)
    }

    /**
     * 发送粘性事件消息
     *
     * @param event
     */
    fun postSticky(event: EventMessage<*>) {
        EventBus.getDefault().postSticky(event)
    }

}
