var SockJS = require('sockjs-client');
var Stomp = require('stompjs');
var React = require('react');

var App = React.createClass({
    getInitialState: function () {
        return {messages: [], connected: false, input: ''};
    },
    connect: function () {
        var that = this;
        var socket = new SockJS('/message');
        this.stompClient = Stomp.over(socket);
        this.stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            that.setState({connected: true});
            that.stompClient.subscribe('/chat/messages', function (message) {
                var messages = that.state.messages.slice(0);
                messages.push(JSON.parse(message.body).content);
                that.setState({messages: messages});
            });
        });
    },
    disconnect: function () {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
        this.setState({connected: false});
        console.log("Disconnected");
    },
    handleInput: function (event) {
        this.setState({input: event.target.value});
    },
    send: function () {
        this.stompClient.send("/messaging/message", {}, JSON.stringify({content: this.state.input}));
    },
    render: function () {
        return <div>
            <p>Connected: {this.state.connected ? 'Yes' : 'No'}</p>
            <input type="button"
                   value={this.state.connected ? "Disconnect" : "Connect"}
                   onClick={this.state.connected ? this.disconnect : this.connect}/>

            <input type="text" onChange={this.handleInput}/>
            <input type="button" value="Send" onClick={this.send}/>
            <ul>
                {this.state.messages.map(function (message, index) {
                    return <li key={index}>{message}</li>
                })}
            </ul>
        </div>;
    }
});

React.render(<App/>, document.getElementById('app'));
