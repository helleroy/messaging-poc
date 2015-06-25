var SockJS = require('sockjs-client');
var Stomp = require('stompjs');
var React = require('react');

var App = React.createClass({
    getInitialState: function () {
        return {messages: [], users: [], connected: false, input: '', toUser: ''};
    },
    componentDidMount: function () {
        this.connect();
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
    getMessages: function () {
        var r = new XMLHttpRequest();
        r.open("GET", "/messages");
        r.onreadystatechange = function () {
            if (r.readyState != 4 || r.status != 200) return;
            this.setState({messages: JSON.parse(r.responseText)});
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
                messages.push(JSON.parse(message.body));
                that.setState({messages: messages});
            });
            that.stompClient.subscribe('/user/chat/messages', function (message) {
                var messages = that.state.messages.slice(0);
                messages.push(JSON.parse(message.body));
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

            that.getUsers();
            that.getMessages();
        });
    },
    disconnect: function () {
        if (this.stompClient != null) {
            this.stompClient.disconnect();
        }
        this.setState({connected: false});
        console.log("Disconnected");
    },
    handleChatInput: function (event) {
        this.setState({input: event.target.value});
    },
    updateToUser: function (user) {
        var name = user.name === this.state.toUser ? '' : user.name;
        return function () {
            this.setState({toUser: name});
        }.bind(this);
    },
    send: function (event) {
        event.stopPropagation();
        event.preventDefault();
        var prefix = '';
        prefix += this.state.toUser.length !== 0 ? '/user/' + this.state.toUser : ''
        var channel = '/message';
        channel = prefix + channel;
        this.stompClient.send(channel, {}, JSON.stringify({message: this.state.input}));
        this.setState({input: ''});
    },
    render: function () {
        return <div className="chat">
            <section className="grid header">
                Connected: {this.state.connected ? 'Yes' : 'No'}&nbsp;
                <input type="button"
                       className="button-small"
                       value={this.state.connected ? "Disconnect" : "Reconnect"}
                       onClick={this.state.connected ? this.disconnect : this.connect}/>
            </section>
            <section className="grid main">
                <div className="col-1-5 sidebar chat-users">
                    <ul>
                        {Object.keys(this.state.users).map(function (user) {
                            return <li key={user}
                                       className="sidebar-list-element"
                                       onClick={this.updateToUser(this.state.users[user])}>
                                {this.state.users[user].name}
                            </li>
                        }.bind(this))}
                    </ul>
                </div>
                <div className="col-4-5 chat-main">
                    <div className="grid module chat-messages">
                        <ul>
                            {this.state.messages.map(function (message, index) {
                                return <li key={index}>{message.sender + ': ' + message.message}</li>
                            })}
                        </ul>
                    </div>
                    <div className="chat-form-wrapper">
                        <form className="grid col-4-5 module chat-form" onSubmit={this.send}>
                            Sending to {this.state.toUser.length !== 0 ? this.state.toUser : 'everyone'}
                            <input type="text" className="textfield textfield-message" value={this.state.input}
                                   onChange={this.handleChatInput}/>
                            <input type="submit" className="button-large button-send" value="Send"/>
                        </form>
                    </div>
                </div>
            </section>
        </div>;
    }
});

React.render(<App/>, document.getElementById('app'));
