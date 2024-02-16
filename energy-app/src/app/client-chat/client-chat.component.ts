import { Component, OnInit } from '@angular/core';
import { ChatWebsocketShareService } from '../core/chat-websocket/chat-websocket-share.service';
import { ChatWebsocket } from '../core/chat-websocket/chat-websocket';
import { Message } from '../core/message/message';
import { TypingMessage } from '../core/typing-message/typing-message';
import { SeenMessage } from '../core/seen-message/seen-message';
import { UserService } from '../core/user/user.service';
import { Observable } from 'rxjs';
import { User } from '../core/user/user';

@Component({
  selector: 'app-client-chat',
  templateUrl: './client-chat.component.html',
  styleUrls: ['./client-chat.component.css']
})
export class ClientChatComponent implements OnInit  {

  websocketService : ChatWebsocketShareService = new ChatWebsocketShareService();
  webSocketAPI : ChatWebsocket = new ChatWebsocket(this.websocketService);
  wsData: any = 'Hello'; 

  currentClient : string | undefined;
  adminToChat : User | undefined ;

  messages: Message[] = [];

  newMessage: string | undefined;
  newReceivedMessage : string | undefined;

  displayTypingNotification : boolean | undefined;
  
  displaySeenNotification : boolean | undefined;

  

  newUnreadMessage : Message[] = [];

  constructor(private userService : UserService) {
  }

  ngOnInit(): void {
    this.displayTypingNotification = false;
    this.currentClient = localStorage.getItem("userid")!;
    console.log("current admin:", this.currentClient);
    this.webSocketAPI._connect();          
    this.onNewValueReceive();
    this.getAdmin();
    console.log("admin id este:", this.getAdminId());
    console.log("tipul id-ului: ", typeof(this.getAdminId()));
  }

  connect() {
    this.webSocketAPI._connect();
  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  
  getAdmin() {
    this.userService.getAdminID().subscribe(data => {
      this.adminToChat = data;
    });
  }
  
  getAdminId(): string | undefined {
    console.log(this.adminToChat?.userID?.toString());
    return this.adminToChat?.userID?.toString();
  }

  onNewValueReceive() {
    this.websocketService.getNewValue().subscribe(resp => {
      this.wsData = resp;
      const parsedData = JSON.parse(this.wsData.body);
      //console.log("parsed data:", parsedData);

      if(parsedData.reciever==this.currentClient) {
        //mesaj de tip typing
        if (parsedData.typing==true) {
          if(parsedData.typing==true) {
            this.displayTypingNotification = true;
            setTimeout(() => {
              // Dezactivează notificarea de scriere după 2 secunde
              this.displayTypingNotification = false;
            }, 2000);
          }
        }
        //mesaj de seen
        else {
          if(parsedData.seen==true) {
            this.displaySeenNotification = true;
            console.log("Admin has seen the message");
          }
          else {
            console.log("primit mesaj normal");
            const recievedMessage: Message = {
              sender: parsedData.sender,
              reciever: this.currentClient,
              information: parsedData.information,
              timeSent: parsedData.timeSent
            };
            this.messages.push(recievedMessage);
            this.newUnreadMessage.push(recievedMessage);
            this.displaySeenNotification = false;
            console.log(this.newUnreadMessage.length);
          }
         
          
        }
      }
    });
  }

  sendMessage() {
    if (this.newMessage?.trim() !== '') {
      this.displaySeenNotification = false;
        // Create a Message object to send
        const messageToSend: Message = {
            sender: this.currentClient,
            reciever: this.getAdminId(),
            information: this.newMessage,
            timeSent: undefined
        };

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
    console.log("Si acesta se triggeruieste");
    // Puteți adăuga aici logica suplimentară sau apelați alte funcții
    if (this.newMessage?.trim() !== '') {
      // Create a Message object to send
      const typingMessage: TypingMessage = {
          sender: this.currentClient,
          reciever: this.getAdminId(),
          isTyping : true
      };
      this.webSocketAPI.sendMessage('/app/typing', typingMessage);
    }
  }

  onInputFocus() {
    // Funcția care va fi apelată atunci când utilizatorul dă focus pe input
    if(this.newUnreadMessage.length>0) {
      const seenMessage: SeenMessage = {
        sender: this.currentClient,
        reciever: this.getAdminId(),
        seen : true
      };
      this.webSocketAPI.sendMessage('/app/seen', seenMessage);
      this.newUnreadMessage = [];
      console.log('Input is focused!');
    }
    
    // Puteți adăuga orice altă logică aici
  }

}
