import { Injectable } from "@angular/core";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { ChatWebsocketShareService } from "./chat-websocket-share.service";
 

@Injectable()
export class ChatWebsocket {
 
    webSocketEndPoint: string = 'http://localhost:8083/ws';
    topic: string = '/topic/chat';
    typingtopic: string = '/topic/typing';
    seentopic: string = '/topic/seen';
    stompClient: any;

    constructor(private websocketShare: ChatWebsocketShareService){
         
    }

    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS(this.webSocketEndPoint);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        _this.stompClient.connect({}, function () {
            console.log("Connected??");
            // _this.stompClient.subscribe('/topic/chat', (message: any) => {
            //     _this.websocketShare.onNewValueReceive(JSON.parse(message.body));
            // });
            _this.stompClient.subscribe(_this.topic, function (sdkEvent: string) {
                _this.onMessageReceived(sdkEvent);
            });

            _this.stompClient.subscribe(_this.typingtopic, function (sdkEvent: string) {
                _this.onMessageReceived(sdkEvent);
            });

            _this.stompClient.subscribe(_this.seentopic, function (sdkEvent: string) {
                _this.onMessageReceived(sdkEvent);
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
        this.websocketShare.onNewValueReceive(message);
    }

    sendMessage(destination: string, message: any) {
    // Send a message to the specified destination
        this.stompClient.send(destination, {}, JSON.stringify(message));
    }
}


