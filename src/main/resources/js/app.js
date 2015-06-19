var SockJS = require('sockjs-client');
var Stomp = require('stompjs');
var React = require('react');

var App = React.createClass({
    getInitialState: function () {
        return {messages: [], users: [], connected: false, input: ''};
    },
    componentDidMount: function () {
        this.getUsers();
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
            that.stompClient.subscribe('/chat/users', function (message) {
                var users = that.state.users.slice(0);
                users.push(JSON.parse(message.body));
                that.setState({users: users});
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
    send: function (event) {
        event.stopPropagation();
        event.preventDefault();
        this.stompClient.send("/messaging/message", {}, JSON.stringify({content: this.state.input}));
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
                <input type="text" value={this.state.input} onChange={this.handleInput}/>&nbsp;
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
                    {this.state.users.map(function (message, index) {
                        return <li key={index}>{message}</li>
                    })}
                </ul>
            </section>
        </div>;
    }
});

React.render(<App/>, document.getElementById('app'));
