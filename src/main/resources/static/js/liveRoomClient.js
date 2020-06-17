
class LiveRoomClient {

    constructor(config) {
        this.config = config;
    }

    connect(onConnect, subscriber) {
        const socket = new SockJS(this.config.host);
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, (frame) => {
            console.log('Connected: ' + frame);
            this.stompClient.subscribe(this.config.subscribeTo, function(messageOutput) {
                subscriber(JSON.parse(messageOutput.body))
            });
            onConnect();
        });
    }

    disconnect() {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    sendAction(action) {
        this.stompClient.send(this.config.sendTo, {},
            JSON.stringify({ 'username': this.config.username, 'action': action }));
    }


}