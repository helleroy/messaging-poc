var SockJS = require('sockjs-client');
var Stomp = require('stompjs');
var React = require('react');

var App = React.createClass({
    getInitialState: function () {
        return {messages: [], users: [], connected: false, input: '', toUser: ''};
    },
    componentDidMount: function () {
        this.connect();
        this.getUsers();
    },
    getUsers: function () {
        var r = new XMLHttpRequest();
        r.open("GET", "/users");
        r.onreadystatechange = function () {
            if (r.readyState != 4 || r.status != 200) return;
            this.setState({users: JSON.parse(r.responseText)});
        }.bind(this);
        r.send();
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
            that.stompClient.subscribe('/user/chat/messages', function (message) {
                var messages = that.state.messages.slice(0);
                messages.push(JSON.parse(message.body).content);
                that.setState({messages: messages});
            });
            that.stompClient.subscribe('/chat/users', function (message) {
                var connectionMessage = JSON.parse(message.body);
                if (connectionMessage.connected) {
                    that.state.users[connectionMessage.user.name] = connectionMessage.user;
                } else {
                    delete that.state.users[connectionMessage.user.name];
                }
                that.setState({users: that.state.users});
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
    handleUsernameInput: function (event) {
        this.setState({toUser: event.target.value});
    },
    handleChatInput: function (event) {
        this.setState({input: event.target.value});
    },
    send: function (event) {
        event.stopPropagation();
        event.preventDefault();
        var channel = '/messaging/message';
        channel += this.state.toUser.length !== 0 ? '.user.' + this.state.toUser : '';
        this.stompClient.send(channel, {}, JSON.stringify({content: this.state.input}));
        this.setState({input: ''});
    },
    render: function () {
        return <div>
            <p>
                Connected: {this.state.connected ? 'Yes' : 'No'}&nbsp;
                <input type="button"
                       value={this.state.connected ? "Disconnect" : "Reconnect"}
                       onClick={this.state.connected ? this.disconnect : this.connect}/>
            </p>

            <form onSubmit={this.send}>
                <label>
                    <p>Send to user</p>
                    <input type="text" value={this.state.toUser} onChange={this.handleUsernameInput}/>
                </label>
                <label>
                    <p>Message</p>
                    <input type="text" value={this.state.input} onChange={this.handleChatInput}/>&nbsp;
                </label>
                <input type="submit" value="Send"/>
            </form>
            <section>
                Messages:
                <ul>
                    {this.state.messages.map(function (message, index) {
                        return <li key={index}>{message}</li>
                    })}
                </ul>
            </section>
            <section>
                Users:
                <ul>
                    {Object.keys(this.state.users).map(function (user) {
                        return <li key={user}>{this.state.users[user].name}</li>
                    }.bind(this))}
                </ul>
            </section>
        </div>;
    }
});

React.render(<App/>, document.getElementById('app'));
