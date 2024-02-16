import { Component, OnInit } from '@angular/core';
import { WebSocketShareService } from '../core/websocket/web-socket-share.service';
import { WebSocketAPI } from '../core/websocket/web-socket-api';
import { HttpClient } from '@angular/common/http';
import { Message } from '../core/message/message';
import { ChatWebsocket } from '../core/chat-websocket/chat-websocket';
import { ChatWebsocketShareService } from '../core/chat-websocket/chat-websocket-share.service';
import { TypingMessage } from '../core/typing-message/typing-message';
import { SeenMessage } from '../core/seen-message/seen-message';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit  {

  //socket
  websocketService : ChatWebsocketShareService = new ChatWebsocketShareService();
  webSocketAPI : ChatWebsocket = new ChatWebsocket(this.websocketService);
  wsData: any = 'Hello'; 

  currentAdmin : string | undefined;
  clientToChat : string | undefined;


  messages: Message[] = [];
  newUnreadMessage : Message[] = [];

  newMessage: string | undefined;
  newReceivedMessage : string | undefined;

  displayTypingNotification : boolean | undefined;
  displaySeenNotification : boolean | undefined;

  constructor(private httpClient : HttpClient) { }

  ngOnInit(): void {
    this.currentAdmin = localStorage.getItem("userid")!;
    console.log("current admin:", this.currentAdmin);
    this.webSocketAPI._connect();          
    this.onNewValueReceive();
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  // onNewValueReceive() {
  //   this.websocketService.getNewValue().subscribe(resp => {
  //     this.wsData = resp;

  //     const receivedMessage: Message = {
  //       sender: this.clientToChat,
  //       reciever: this.currentAdmin,
  //       information: this.newMessage,
  //       timeSent: undefined
  //   };

  //     this.messages.push(receivedMessage);
  //     //this.newMessage = '';
  //     console.log("wsDATA de la admin : ", this.wsData.body.information);
  //   });
  // }

  onNewValueReceive() {
    this.websocketService.getNewValue().subscribe(resp => {
      this.wsData = resp;
      const parsedData = JSON.parse(this.wsData.body);
      if(parsedData.reciever==this.currentAdmin) {
        //mesaj de tip typing
        if (parsedData.typing==true) {
          if(parsedData.typing==true) {
            this.displayTypingNotification = true;
            setTimeout(() => {
              // Dezactivează notificarea de scriere după 2 secunde
              this.displayTypingNotification = false;
            }, 2000);
          }
          console.log("Am primit notificarea de typing client!");
        }
        else {
          if(parsedData.seen==true) {
            this.displaySeenNotification = true;
            console.log("Client has seen the message");
          }
          else {
            const receivedMessage: Message = {
              sender: parsedData.sender,
              reciever: this.currentAdmin,
              information: parsedData.information,
              timeSent: parsedData.timeSent
            };
            this.newUnreadMessage.push(receivedMessage);
            this.messages.push(receivedMessage);
            this.newMessage = '';
            this.displaySeenNotification = false;
          }
        }
      }        
      console.log("information admin:", this.wsData.body);
    
    });
  }

  sendMessage() {
    if (this.newMessage?.trim() !== '') {
        this.displaySeenNotification=false;
        // Create a Message object to send
        const messageToSend: Message = {
            sender: this.currentAdmin,
            reciever: this.clientToChat,
            information: this.newMessage,
            timeSent: undefined
        };
        
        this.displayTypingNotification = false;
        
        // Send the message to the backend via WebSocket
        this.webSocketAPI.sendMessage('/app/send', messageToSend);

        // Optional: Add the sent message to your local messages array for display
        this.messages.push(messageToSend);
        this.newMessage = '';
        // Optional: Auto-scroll to the bottom of the chat box
        const chatBox = document.getElementById('chat-box');
        if (chatBox) {
            chatBox.scrollTop = chatBox.scrollHeight;
        }
    }
  }

  onInputStart() {
    console.log('Input has started.');
    // Puteți adăuga aici logica suplimentară sau apelați alte funcții
    if (this.newMessage?.trim() !== '') {
      // Create a Message object to send
      const typingMessage: TypingMessage = {
          sender: this.currentAdmin,
          reciever: this.clientToChat,
          isTyping : true
      };
      this.webSocketAPI.sendMessage('/app/typing', typingMessage);
    }
  }

  onInputFocus() {
    // Funcția care va fi apelată atunci când utilizatorul dă focus pe input
    if(this.newUnreadMessage.length>0) {
      const seenMessage: SeenMessage = {
        sender: this.currentAdmin,
        reciever: this.clientToChat,
        seen : true
      };
      this.webSocketAPI.sendMessage('/app/seen', seenMessage);
      this.newUnreadMessage = [];
      console.log('Input is focused!');
    }
    
    // Puteți adăuga orice altă logică aici
  }
  
}
