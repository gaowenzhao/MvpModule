package com.zhao.base.utils.eventbus

class EventMessage<T>(var code:Int,var data:T?=null){
    override fun toString(): String {
        return "EventMessage{code= $code, data=$data}"
    }
}