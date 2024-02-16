import { Injectable } from "@angular/core";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { WebSocketShareService } from "./web-socket-share.service";
 

@Injectable()
export class WebSocketAPI {
 
    webSocketEndPoint: string = 'http://localhost:8082/ws';
    topic: string = '/topic/notification';
    stompClient: any;

    constructor(private websocketShare: WebSocketShareService){
         
    }

    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function () {
            console.log("Connected??");
            _this.stompClient.subscribe(_this.topic, function (sdkEvent: string) {
                _this.onMessageReceived(sdkEvent);
                // console.log('Subscribeee');
                console.log('sdk event:', sdkEvent);
            });
            _this.stompClient.reconnect_delay = 2000;
        }, this.errorCallBack);
    };

    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }
    // on error, schedule a reconnection attempt
    errorCallBack(error: String) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this._connect();
        }, 5000);
    }  

    onMessageReceived(message : string) {   
        console.log("Message received??"); 
        this.websocketShare.onNewValueReceive(message);
    }
}


